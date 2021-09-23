package com.felipepolo.theleagues.presentation

import androidx.lifecycle.*
import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.data.model.League
import com.felipepolo.theleagues.data.model.Team
import com.felipepolo.theleagues.domain.SportRepositoryInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val sportRepositoryInt: SportRepositoryInt) :
    ViewModel() {

    private var currentLeague: String = "Spanish La Liga"

    private var _teamList: MutableLiveData<Resource<List<Team>>> = MutableLiveData(Resource.Loading)
    val teamList: LiveData<Resource<List<Team>>> = _teamList


    init {
        teamListByLeague()
    }

    private fun teamListByLeague() {
        viewModelScope.launch{
            try {
                _teamList.value = sportRepositoryInt.getTeamListByLeague(currentLeague)
            } catch (e: Exception) {
                _teamList.value = Resource.Failure(e)
            }
        }
    }

    fun setLeague(league: String) {
        if(currentLeague != league){
            currentLeague = league
            teamListByLeague()
        }
    }

}