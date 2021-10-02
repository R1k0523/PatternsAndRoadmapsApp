package ru.boringowl.parapp.presentation.view.posts

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.DocumentsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.AddNoteFragmentBinding
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.view.posts.adapters.AddSectionListAdapter
import ru.boringowl.parapp.presentation.view.posts.adapters.DocsListAdapter
import ru.boringowl.parapp.presentation.viewmodel.posts.AddNoteViewModel
import java.text.SimpleDateFormat
import java.util.*
import android.app.Activity
import android.util.Log

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult


class AddNoteFragment : Fragment() {

    private lateinit var viewModel: AddNoteViewModel
    private lateinit var binding: AddNoteFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddNoteFragmentBinding.inflate(layoutInflater, container, false)
        binding.saveButton.setOnClickListener {
            val currentDateTime = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(Date())
            if (binding.title.text.isNotEmpty() && binding.description.text.isNotEmpty() && binding.category.text.isNotEmpty()) {
                viewModel.save(
                    Note(
                        title = binding.title.text.toString(),
                        image = viewModel.image.value,
                        postDescription = binding.description.text.toString(),
                        publicationDateTime = currentDateTime,
                        postCategories = binding.category.text.split(" "),
                        sections = (binding.recyclerViewSection.adapter as AddSectionListAdapter).data,
                        docs = (binding.recyclerViewFiles.adapter as DocsListAdapter).dataToString(),
                    )
                )
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Вы ввели не все данные", Toast.LENGTH_SHORT).show()
            }
        }
        binding.recyclerViewSection.also {
            it.layoutManager = LinearLayoutManager(context)
            it.setHasFixedSize(false)
            it.adapter =  AddSectionListAdapter(viewLifecycleOwner)
        }
        binding.addSection.setOnClickListener {
            (binding.recyclerViewSection.adapter as AddSectionListAdapter).addSection()

        }
        binding.titleSection.setOnClickListener {
            if (binding.isSectionOpened == null) {
                binding.isSectionOpened = false
            }
            var isSectionOpened: Boolean?  = binding.isSectionOpened
            isSectionOpened = !isSectionOpened!!
            binding.isSectionOpened = isSectionOpened
            binding.sectionArrow.setImageResource(arrow(isSectionOpened))
        }
        binding.titleFiles.setOnClickListener {
            if (binding.isFileOpened == null)
                binding.isFileOpened = false
            var isFileOpened: Boolean? = binding.isFileOpened
            isFileOpened = !isFileOpened!!
            binding.isFileOpened = isFileOpened
            binding.fileArrow.setImageResource(arrow(isFileOpened))
        }
        binding.recyclerViewFiles.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(false)
            this.adapter =  DocsListAdapter(true)
        }
        binding.buttonFile.setOnClickListener {
            openFile()
        }
        binding.addPhotoButton.setOnClickListener {
            renewPhoto()
        }
        binding.mainImage.setOnLongClickListener {
            viewModel.setImage(null)
            true
        }
        binding.mainImage.setOnClickListener {
            renewPhoto()
        }
        return binding.root
    }

    private fun arrow(isOpened: Boolean): Int {
        return if (isOpened)
            R.drawable.ic_round_keyboard_arrow_up_24
        else
            R.drawable.ic_round_keyboard_arrow_down_24
    }

    private fun setPhoto() {
        val uri = Uri.parse(viewModel.image.value)
        binding.mainImage.setImageBitmap(
            BitmapFactory.decodeFileDescriptor(
                requireContext().contentResolver.openFileDescriptor(
                    uri, "r")?.fileDescriptor
            )
        )
        binding.mainImage.visibility = View.VISIBLE
    }

    private fun renewPhoto() {
        requireActivity().activityResultRegistry.register(
            "key",
            ActivityResultContracts.OpenDocument()
        ) { result ->
            if (result != null) {
                requireActivity().applicationContext.contentResolver
                    .takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                binding.addPhotoButton.visibility = View.GONE
                viewModel.setImage(result.toString())
            }
        }.launch(arrayOf("image/*"))
    }

    private fun addFile() {
        requireActivity().activityResultRegistry.register(
            "key",
            ActivityResultContracts.OpenDocument()
        ) { result ->
            if (result != null) {
                requireActivity().applicationContext.contentResolver
                    .takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                (binding.recyclerViewFiles.adapter as DocsListAdapter).data.add(result)
                (binding.recyclerViewFiles.adapter as DocsListAdapter).notifyDataSetChanged()
            }
        }.launch(arrayOf("*/*"))
    }
    fun openFile() {
        requireActivity().activityResultRegistry.register(
            "key",
            ActivityResultContracts.OpenDocument()
        ) { result ->
            if (result != null) {
                requireActivity().applicationContext.contentResolver
                    .takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                (binding.recyclerViewFiles.adapter as DocsListAdapter).data.add(result)
                (binding.recyclerViewFiles.adapter as DocsListAdapter).notifyDataSetChanged()
            }
        }.launch(arrayOf("application/pdf", "text/*", "image/*"))



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        viewModel.image.observe(viewLifecycleOwner, {
            if (it == null) {
                binding.mainImage.setImageDrawable(null)
                binding.addPhotoButton.visibility = View.VISIBLE
                binding.mainImage.visibility = View.GONE
            } else
                setPhoto()
        })
    }

}