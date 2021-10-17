package com.example.mobirollertask.ui.productAdd

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mobirollertask.R
import com.example.mobirollertask.databinding.FragmentProductAddBinding
import com.example.mobirollertask.models.entity.Product
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_product_list.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ProductAddFragment : Fragment() {
    private var _binding: FragmentProductAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var selectedBitmap: Bitmap? = null
    var selectedImageUri: Uri? = null
    private val viewModel: ProductAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductAddBinding.inflate(inflater, container, false)
        registerLauncher()
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.apply {
            selectImageButton.setOnClickListener {
                checkSelfPermission(
                    requireContext(),
                    requireActivity(),
                    binding.root,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    "Gallery"
                )
            }
            saveButton.setOnClickListener {
                val currentDateTime = SimpleDateFormat("dd/M/yyyy")
                val product = Product(
                    imageUri = selectedImageUri.toString(),
                    title = textFieldProductTitle.editText!!.text.toString(),
                    category = textFieldProductCategory.editText!!.text.toString(),
                    description = textFieldProductDescription.editText!!.text.toString(),
                    price = textFieldProductPrice.editText!!.text.toString(),
                    uploadDate = currentDateTime.format(Date())
                )

                if (validate()) {
                    viewModel.addProduct(product).observe(viewLifecycleOwner, {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        clearEditTexts()
                        clearError()
                    })
                }
            }
        }
    }

    private fun validate(): Boolean {
        var result = true
        binding.apply {
            if (textFieldProductTitle.editText!!.text.isNullOrEmpty()) {
                textFieldProductTitle.error = "Please enter title"
                result = false
            }
            if (textFieldProductCategory.editText!!.text.isNullOrEmpty()) {
                textFieldProductCategory.error = "Please enter category"
                result = false
            }
            if (textFieldProductDescription.editText!!.text.isNullOrEmpty()) {
                textFieldProductDescription.error = "Please enter description"
            result = false
            }
            if (textFieldProductPrice.editText!!.text.isNullOrEmpty()) {
                textFieldProductPrice.error = "Please enter price"
                result = false
            }
            if(selectedImageUri ==null){
                Toast.makeText(context, "Please select image", Toast.LENGTH_LONG).show()
                result =false
            }
            Log.v("result",result.toString())
        }
        return result
    }

    private fun clearEditTexts() {
        binding.apply {
            imageView.setImageResource(R.drawable.image)
            textFieldProductTitle.editText!!.text.clear()
            textFieldProductCategory.editText!!.text.clear()
            textFieldProductDescription.editText!!.text.clear()
            textFieldProductPrice.editText!!.text.clear()
        }
    }
    private fun clearError(){
        binding.apply {
            textFieldProductTitle.error = null
            textFieldProductCategory.error = null
            textFieldProductDescription.error = null
            textFieldProductPrice.error = null
        }
    }


    fun checkSelfPermission(
        context: Context,
        activity: Activity,
        view: View,
        permission: String,
        permissionName: String
    ) {
        val snackBar =
            Snackbar.make(view, "Permission needed for $permissionName", Snackbar.LENGTH_INDEFINITE)
        if (ContextCompat.checkSelfPermission(
                context,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                //rational
                snackBar.setAction("Give Permission") {
                    //request permission
                    permissionLauncher.launch(permission)
                }
                snackBar.show()
            } else {
                //request permission
                permissionLauncher.launch(permission)
            }
        } else {
            val intentToGallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)
        }
    }


    private fun registerLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    val intentFromResult = result.data
                    if (intentFromResult != null) {
                        val imageData = intentFromResult.data
                        selectedImageUri = imageData
                        //imageView.setImageURI(imageData)
                        if (imageData != null) {
                            try {
                                if (Build.VERSION.SDK_INT >= 28) {
                                    val source = ImageDecoder.createSource(
                                        requireActivity().contentResolver,
                                        imageData
                                    )
                                    selectedBitmap = ImageDecoder.decodeBitmap(source)
                                    binding!!.imageView.setImageBitmap(selectedBitmap)
                                } else {
                                    selectedBitmap = MediaStore.Images.Media.getBitmap(
                                        requireActivity().contentResolver,
                                        imageData
                                    )
                                    binding!!.imageView.setImageBitmap(selectedBitmap)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    //permission granted
                    val intentToGallery =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intentToGallery)
                } else {
                    //permission denied
                    Toast.makeText(context, "Permission Needed", Toast.LENGTH_LONG).show()
                }
            }
    }
}