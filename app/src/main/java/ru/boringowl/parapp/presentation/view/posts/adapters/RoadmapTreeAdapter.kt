package ru.boringowl.parapp.presentation.view.posts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.bandb.graphview.AbstractGraphAdapter
import dev.bandb.graphview.graph.Graph
import dev.bandb.graphview.graph.Node
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.RoadmapTreeItemBinding
import ru.boringowl.parapp.domain.model.posts.roadmaps.RoadmapNode

class RoadmapTreeAdapter(val parent: RoadmapNode) : AbstractGraphAdapter<RoadmapTreeAdapter.NodeViewHolder>() {
        private var nodesGraph = Graph()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
            val binding: RoadmapTreeItemBinding =
                RoadmapTreeItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            return NodeViewHolder(binding)
        }

        override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
            val node = (getNodeData(position) as RoadmapNode)
            holder.binding.description.text = node.description
            holder.binding.title.text = node.title
            var darkColor = ContextCompat.getColor(holder.binding.root.context, R.color.orange_700)
            var lightColor = ContextCompat.getColor(holder.binding.root.context, R.color.grey_500)
            holder.binding.root.setBackgroundColor(if(node.isMainWay) darkColor else lightColor)
            holder.binding.title.setOnClickListener{
                holder.binding.title.visibility =
                    if (holder.isOpened)
                        View.GONE
                    else
                        View.VISIBLE
                holder.binding.description.visibility =
                    if (holder.isOpened)
                        View.VISIBLE
                    else
                        View.GONE
                holder.isOpened = !holder.isOpened
            }
        }

        private fun getTree(node : RoadmapNode) {
            val parentNode = Node(node.title)
            parentNode.data = node
            nodesGraph.addNode(parentNode)
            node.childrenNodes.forEach {
                val child = Node(it.title)
                child.data = it
                nodesGraph.addNode(child)
                nodesGraph.addEdge(parentNode, child)
                getTree(it)
            }
        }
        fun drawTree() {
            getTree(parent)
            submitGraph(nodesGraph)
        }

        class NodeViewHolder(val binding: RoadmapTreeItemBinding, var isOpened: Boolean = false) : RecyclerView.ViewHolder(binding.root)
}