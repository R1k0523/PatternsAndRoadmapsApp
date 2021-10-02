package ru.boringowl.parapp.presentation.view.posts

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.NoteFragmentBinding
import ru.boringowl.parapp.presentation.utils.ImageUtils
import ru.boringowl.parapp.presentation.viewmodel.factory.NoteViewModelFactory
import ru.boringowl.parapp.presentation.viewmodel.posts.NoteViewModel
import java.lang.Exception

class NoteFragment : Fragment() {

    private val args: NoteFragmentArgs by navArgs()
    private val viewModel: NoteViewModel by viewModels {NoteViewModelFactory(args.noteId)}
    private lateinit var binding: NoteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoteFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.note.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.note = it
                if (it.docs == null) {
                    binding.files.visibility = View.GONE
                } else if (it.docs != null) {
                    if (it.docs!!.isEmpty())
                        binding.files.visibility = View.GONE
                }
                it.postCategories.forEach { text ->
                    val textview = TextView(context).also {tv ->
                        tv.text = text
                        tv.textSize = 12f
                        tv.minEms = 5
                        tv.gravity = Gravity.CENTER
                        tv.setPadding(8, 8, 8, 8)
                        tv.setBackgroundResource(R.drawable.category_shape)
                        tv.setTextColor(Color.BLACK)
                        val params = RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                        )
                        params.setMargins(4, 0, 4, 0)
                        tv.layoutParams = params
                    }
                    binding.categories.addView(textview)
                }
                if (it.image != null) {
                    try {
                        binding.mainImage.setImageBitmap(
                            ImageUtils.uriToBitmap(binding.root.context, Uri.parse(it.image))
                        )
                        binding.mainImage.visibility = View.VISIBLE
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } else {
                findNavController().navigate(NoteFragmentDirections.actionNoteFragmentToNotesListFragment())
            }

        })
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

        binding.shareImage.setOnClickListener {
            if (viewModel.note.value != null) {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://parapp.jun/notes/${viewModel.note.value!!.id}"
                )
                sendIntent.type = "text/plain"
                val shareIntent: Intent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }

    }

    private fun arrow(isOpened: Boolean): Int {
        return if (isOpened)
            R.drawable.ic_round_keyboard_arrow_up_24
        else
            R.drawable.ic_round_keyboard_arrow_down_24
    }

}

