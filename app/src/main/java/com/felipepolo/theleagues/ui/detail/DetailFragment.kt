package com.felipepolo.theleagues.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.felipepolo.pokedex.utils.ShowSnack
import com.felipepolo.pokedex.utils.setImageFromUrl
import com.felipepolo.pokedex.utils.setupSocialIf
import com.felipepolo.theleagues.R
import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.core.ViewModelFactory
import com.felipepolo.theleagues.databinding.FragmentDetailBinding
import com.felipepolo.theleagues.presentation.DetailViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }

    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupEventList()
    }

    private fun setupEventList() {
        viewModel.getTeamEvents(args.team)
        viewModel.teamListEvent.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val adapter = ArrayAdapter<String>(
                        requireContext(),
                        R.layout.event_list_item,
                        R.id.tvEvent,
                        it.data.map { data -> data.strEvent })
                    binding.lvEvents.adapter = adapter
                }
                is Resource.Failure -> {
                    binding.root.ShowSnack("Error: ${it.exception.message}")
                }
            }
        })
    }

    private fun setupView() {
        binding.tvTeamDetailName.text = args.team.strTeam
        binding.tvDetailDescription.text = args.team.strDescriptionES
        binding.tvDetailFoundation.text = args.team.intFormedYear
        binding.ivDetailBadge.setImageFromUrl(args.team.strTeamBadge!!)
        binding.ivDetailJersey.setImageFromUrl(args.team.strTeamJersey!!)
        binding.ivWebpage.setupSocialIf(args.team.strWebsite!!) { openUrl("https://${args.team.strWebsite}") }
        binding.ivFacebook.setupSocialIf(args.team.strFacebook!!) { openUrl("https://${args.team.strFacebook}") }
        binding.ivTwitter.setupSocialIf(args.team.strTwitter!!) { openUrl("https://${args.team.strTwitter}") }
        binding.ivInstagram.setupSocialIf(args.team.strInstagram!!) { openUrl("https://${args.team.strInstagram}") }
        binding.ivYoutube.setupSocialIf(args.team.strYoutube!!) { openUrl("https://${args.team.strYoutube}") }
    }

    private fun openUrl(uri: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    }
}