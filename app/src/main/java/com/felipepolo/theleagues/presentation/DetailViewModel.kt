package com.felipepolo.theleagues.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.data.model.Event
import com.felipepolo.theleagues.data.model.TeamEntity
import com.felipepolo.theleagues.domain.SportRepositoryInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val  sportRepositoryInt: SportRepositoryInt) : ViewModel() {

    private var _teamListEvent: MutableLiveData<Resource<List<Event>>> = MutableLiveData()
    val teamListEvent: LiveData<Resource<List<Event>>> = _teamListEvent

    fun getTeamEvents(teamEntity: TeamEntity) {
        viewModelScope.launch {
            _teamListEvent.value = Resource.Loading
            try {
                _teamListEvent.value = withContext(Dispatchers.IO) {
                    sportRepositoryInt.getEventListByTeam(teamEntity.idTeam)
                }
            }catch (e: Exception) {
                _teamListEvent.value = Resource.Failure(e)
            }
        }
    }

}