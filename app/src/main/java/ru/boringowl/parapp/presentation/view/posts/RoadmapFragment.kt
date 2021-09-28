package ru.boringowl.parapp.presentation.view.posts

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
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
import dev.bandb.graphview.layouts.tree.BuchheimWalkerConfiguration
import dev.bandb.graphview.layouts.tree.BuchheimWalkerLayoutManager
import dev.bandb.graphview.layouts.tree.TreeEdgeDecoration
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.RoadmapFragmentBinding
import ru.boringowl.parapp.presentation.view.posts.adapters.RoadmapTreeAdapter
import ru.boringowl.parapp.presentation.viewmodel.factory.RoadmapViewModelFactory
import ru.boringowl.parapp.presentation.viewmodel.posts.RoadmapViewModel
import java.lang.Exception


class RoadmapFragment : Fragment() {

    private val args: RoadmapFragmentArgs by navArgs()
    private val viewModel: RoadmapViewModel by viewModels { RoadmapViewModelFactory(args.roadmapId) }
    private lateinit var binding: RoadmapFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RoadmapFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val configuration = BuchheimWalkerConfiguration.Builder()
            .setSiblingSeparation(100)
            .setLevelSeparation(100)
            .setSubtreeSeparation(100)
            .setOrientation(BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM)
            .build()
        binding.recycler.layoutManager = BuchheimWalkerLayoutManager(requireContext(), configuration)
        val edgeStyle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            strokeWidth = 5f
            color = Color.GRAY
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            pathEffect = CornerPathEffect(10f)
        }
        binding.recycler.addItemDecoration(TreeEdgeDecoration(edgeStyle))
        viewModel.roadmap.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.roadmap = it
                val adapter = RoadmapTreeAdapter(it.root)
                adapter.drawTree()
                binding.recycler.adapter = adapter
            } else {
                findNavController().navigate(RoadmapFragmentDirections.actionRoadmapFragmentToNotesListFragment())
            }
            it.postCategories.forEach { text ->
                val textview = TextView(context).also { tv ->
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
                        BitmapFactory.decodeFileDescriptor(
                            binding.root.context.contentResolver.openFileDescriptor(
                                Uri.parse(it.image), "r"
                            )?.fileDescriptor
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        binding.shareImage.setOnClickListener {
            if (viewModel.roadmap.value != null) {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://parapp.jun/roadmap/${viewModel.roadmap.value!!.id}"
                )
                sendIntent.type = "text/plain"
                val shareIntent: Intent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}