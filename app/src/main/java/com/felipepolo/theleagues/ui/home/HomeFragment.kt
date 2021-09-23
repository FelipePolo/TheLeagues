package com.felipepolo.theleagues.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Switch
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.felipepolo.pokedex.utils.ShowIf
import com.felipepolo.pokedex.utils.ShowSnack
import com.felipepolo.theleagues.R
import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.core.ViewModelFactory
import com.felipepolo.theleagues.data.model.Team
import com.felipepolo.theleagues.databinding.FragmentHomeBinding
import com.felipepolo.theleagues.presentation.HomeViewModel
import com.felipepolo.theleagues.presentation.HomeViewModel_Factory
import com.felipepolo.theleagues.ui.home.adapters.TeamListAdapter
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

class HomeFragment : DaggerFragment(), TeamListAdapter.OnTeamClickInterface {

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory
    private val viewModel by viewModels<HomeViewModel> { viewmodelFactory }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var teamAdapter: TeamListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTeamList()
    }

    private fun setupTeamList() {
        teamAdapter = TeamListAdapter(requireContext(),this)
        binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTeams.adapter = teamAdapter
        viewModel.teamList.observe(viewLifecycleOwner,{ it ->
            when(it){
                is Resource.Loading -> {
                    binding.progressBar.ShowIf(true)
                }
                is Resource.Success -> {
                    binding.progressBar.ShowIf(false)
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
        binding.ddLeague.setOnItemClickListener { adapterView, view, i, l ->
            viewModel.setLeague(leaguesAdapter.getItem(i)!!)
        }

    }

    override fun onTeamClick(team: Team, position: Int) {

    }

}