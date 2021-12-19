package ru.boringowl.parapp.presentation.view.posts

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.AddNoteFragmentBinding
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.presentation.utils.FileUtils
import ru.boringowl.parapp.presentation.utils.ImageUtils
import ru.boringowl.parapp.presentation.view.posts.adapters.AddSectionListAdapter
import ru.boringowl.parapp.presentation.view.posts.adapters.DocsListAdapter
import ru.boringowl.parapp.presentation.viewmodel.posts.AddNoteViewModel
import java.text.SimpleDateFormat
import java.util.*


class AddNoteFragment : Fragment() {

    private val args: AddNoteFragmentArgs by navArgs()
    private lateinit var viewModel: AddNoteViewModel
    private lateinit var binding: AddNoteFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddNoteFragmentBinding.inflate(layoutInflater, container, false)
        binding.saveButton.setOnClickListener {
            val currentDateTime = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.ENGLISH).format(Date())
            if (binding.title.text.isNotEmpty() && binding.description.text.isNotEmpty() && binding.category.text.isNotEmpty()) {
                val job = viewModel.save(
                    Note(
                        title = binding.title.text.toString(),
                        image = viewModel.image.value,
                        postDescription = binding.description.text.toString(),
                        publicationDateTime = currentDateTime,
                        postCategories = binding.category.text.split(" "),
                        sections = (binding.recyclerViewSection.adapter as AddSectionListAdapter).data,
                        docs = (binding.recyclerViewFiles.adapter as DocsListAdapter).dataToString(),
                        topic = Topic(UUID.fromString(args.topicId)),
                    )
                )
                runBlocking {
                    CoroutineScope(Dispatchers.Main).launch {
                        job.join()
                        findNavController().popBackStack()
                    }
                }
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
            this.adapter =  DocsListAdapter(true, null, context)
        }
        binding.buttonFile.setOnClickListener {
            openFile()
        }
        binding.addPhotoButton.setOnClickListener {
            setImage()
        }
        binding.mainImage.setOnLongClickListener {
            viewModel.setImage(null)
            true
        }
        binding.mainImage.setOnClickListener {
            setImage()
        }
        return binding.root
    }

    private fun arrow(isOpened: Boolean): Int {
        return if (isOpened)
            R.drawable.ic_round_keyboard_arrow_up_24
        else
            R.drawable.ic_round_keyboard_arrow_down_24
    }

    private fun renewImage() {
        val uri = Uri.parse(viewModel.image.value)

        binding.mainImage.setImageBitmap(
            ImageUtils.uriToBitmap(requireContext(), uri)
        )
        binding.mainImage.visibility = View.VISIBLE
    }

    private fun setImage() {
        FileUtils.getFile(requireActivity(), arrayOf("image/*")) { result ->
            binding.addPhotoButton.visibility = View.GONE
            viewModel.setImage(result.toString())
        }
    }

    private fun openFile() {
        FileUtils.getFile(requireActivity(), arrayOf("application/pdf", "text/*", "image/*")) { result ->
            (binding.recyclerViewFiles.adapter as DocsListAdapter).apply {
                this.data.add(result)
                this.notifyDataSetChanged()
            }
        }
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
                renewImage()
        })
    }

}