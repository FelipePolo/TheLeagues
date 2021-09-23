package com.felipepolo.theleagues.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.felipepolo.pokedex.utils.ShowIf
import com.felipepolo.pokedex.utils.ShowSnack
import com.felipepolo.theleagues.R
import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.core.ViewModelFactory
import com.felipepolo.theleagues.data.model.TeamEntity
import com.felipepolo.theleagues.databinding.FragmentHomeBinding
import com.felipepolo.theleagues.presentation.HomeViewModel
import com.felipepolo.theleagues.ui.home.adapters.TeamListAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment(), TeamListAdapter.OnTeamClickInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var teamAdapter: TeamListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTeamList()
    }

    private fun setupTeamList() {
        teamAdapter = TeamListAdapter(requireContext(), this)
        binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTeams.adapter = teamAdapter
        viewModel.teamEntityList.observe(viewLifecycleOwner, {
            binding.progressBar.ShowIf(false)
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.ShowIf(true)
                }
                is Resource.Success -> {
                    teamAdapter.setTeamList(it.data)
                }
                is Resource.Failure -> {
                    binding.root.ShowSnack("Error: ${it.exception.message}")
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        setupSelector()
    }

    private fun setupSelector() {
        val leaguesAdapter = ArrayAdapter(
            requireContext(),
            R.layout.league_dropdown_item,
            resources.getStringArray(R.array.leagues)
        )
        binding.ddLeague.setAdapter(leaguesAdapter)
        binding.ddLeague.setOnItemClickListener { _, _, i, _ ->
            viewModel.setLeague(leaguesAdapter.getItem(i)!!)
        }
    }

    override fun onTeamClick(teamEntity: TeamEntity, position: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(teamEntity)
        findNavController().navigate(action)
    }

}