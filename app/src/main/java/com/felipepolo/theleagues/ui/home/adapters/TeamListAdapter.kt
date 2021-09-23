package com.felipepolo.theleagues.ui.home.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.felipepolo.theleagues.core.BaseViewHolder
import com.felipepolo.theleagues.data.model.TeamEntity
import com.felipepolo.theleagues.databinding.TemplateTeamItemBinding

class TeamListAdapter(
    private val context: Context,
    private val onTeamClickInterface: OnTeamClickInterface
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var teamList = listOf<TeamEntity>()

    interface OnTeamClickInterface {
        fun onTeamClick(teamEntity: TeamEntity, position: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTeamList(newTeamEntityList: List<TeamEntity>){
        teamList = newTeamEntityList
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

    inner class TeamViewHolder(private val binding:TemplateTeamItemBinding): BaseViewHolder<TeamEntity>(binding.root){
        override fun bind(item: TeamEntity, position: Int) {
            Glide.with(context)
                .load(item.strTeamBadge)
                .into(binding.ivTeamBadge)
            binding.tvTeamName.text = item.strTeam
            binding.tvStadium.text = item.strStadium

            binding.teamItem.setOnClickListener { onTeamClickInterface.onTeamClick(item,position)}
        }
    }
}