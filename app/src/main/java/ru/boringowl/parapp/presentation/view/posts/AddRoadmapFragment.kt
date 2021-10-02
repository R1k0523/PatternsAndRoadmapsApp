package ru.boringowl.parapp.presentation.view.posts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.boringowl.parapp.databinding.AddRoadmapFragmentBinding
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.domain.model.posts.roadmaps.RoadmapNode
import ru.boringowl.parapp.presentation.viewmodel.posts.AddRoadmapViewModel
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.BitmapFactory
import android.net.Uri

import ru.boringowl.parapp.presentation.utils.FileUtils


class AddRoadmapFragment : Fragment() {

    private lateinit var viewModel: AddRoadmapViewModel
    private lateinit var binding: AddRoadmapFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddRoadmapFragmentBinding.inflate(layoutInflater, container, false)
        binding.saveButton.setOnClickListener {
            val currentDateTime = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(Date())
            if (binding.title.text.isNotEmpty() && binding.description.text.isNotEmpty() && binding.category.text.isNotEmpty()) {
                viewModel.save(
                    Roadmap(
                        title = binding.title.text.toString(),
                        image = viewModel.image.value,
                        postDescription = binding.description.text.toString(),
                        publicationDateTime = currentDateTime,
                        postCategories = binding.category.text.split(" "),
                        root = RoadmapNode("No Roadmap", "No description", listOf())
                    )
                )
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Вы ввели не все данные", Toast.LENGTH_SHORT).show()
            }
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

    private fun renewImage() {
        val uri = Uri.parse(viewModel.image.value)
        binding.mainImage.setImageBitmap(
            BitmapFactory.decodeFileDescriptor(
                requireContext().contentResolver.openFileDescriptor(
                    uri, "r")?.fileDescriptor
            )
        )
        binding.mainImage.visibility = View.VISIBLE
    }

    private fun setImage() {
        FileUtils.getFile(requireActivity(), arrayOf("image/*")) { result ->
            binding.addPhotoButton.visibility = View.GONE
            viewModel.setImage(result.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddRoadmapViewModel::class.java)
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