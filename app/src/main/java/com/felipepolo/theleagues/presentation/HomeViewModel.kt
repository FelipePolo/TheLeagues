package com.felipepolo.theleagues.presentation

import androidx.lifecycle.*
import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.data.model.TeamEntity
import com.felipepolo.theleagues.domain.SportRepositoryInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val sportRepositoryInt: SportRepositoryInt) :
    ViewModel() {

    private var currentLeague: String = "Spanish La Liga"

    private var _teamEntityList: MutableLiveData<Resource<List<TeamEntity>>> = MutableLiveData()
    val teamEntityList: LiveData<Resource<List<TeamEntity>>> = _teamEntityList


    init {
        teamListByLeague()
    }

    private fun teamListByLeague() {
        _teamEntityList.value = Resource.Loading
        viewModelScope.launch{
            try {
                _teamEntityList.value = withContext(Dispatchers.IO) {
                    sportRepositoryInt.getTeamListByLeague(currentLeague)
                }
            } catch (e: Exception) {
                _teamEntityList.value = Resource.Failure(e)
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