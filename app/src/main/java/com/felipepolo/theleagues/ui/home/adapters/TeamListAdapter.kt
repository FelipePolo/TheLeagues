package com.felipepolo.theleagues.ui.home.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.felipepolo.theleagues.core.BaseViewHolder
import com.felipepolo.theleagues.data.model.Team
import com.felipepolo.theleagues.databinding.TemplateTeamItemBinding

class TeamListAdapter(
    private val context: Context,
    private val onTeamClickInterface: OnTeamClickInterface
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var teamList = listOf<Team>()

    interface OnTeamClickInterface {
        fun onTeamClick(team: Team, position: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTeamList(newTeamList: List<Team>){
        teamList = newTeamList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val binding = TemplateTeamItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is TeamViewHolder -> {
                holder.bind(teamList[position],position)
            }
        }
    }

    override fun getItemCount(): Int {
      return  teamList.size
    }

    inner class TeamViewHolder(val binding:TemplateTeamItemBinding): BaseViewHolder<Team>(binding.root){
        override fun bind(item: Team, position: Int) {
            Glide.with(context)
                .load(item.strTeamBadge)
                .into(binding.ivTeamBadge)
            binding.tvTeamName.setText(item.strTeam)
            binding.tvStadium.setText(item.strStadium)
            // Click Listeners
            binding.teamItem.setOnClickListener { onTeamClickInterface.onTeamClick(item,position)}
        }
    }
}