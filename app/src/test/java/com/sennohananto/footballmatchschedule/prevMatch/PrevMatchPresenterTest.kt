package com.sennohananto.footballmatchschedule.prevMatch

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.sennohananto.footballmatchschedule.matches.prevMatch.PrevMatchPresenter
import com.sennohananto.footballmatchschedule.matches.prevMatch.PrevMatchView
import com.sennohananto.footballmatchschedule.model.Match
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PrevMatchPresenterTest {



    @Mock
    private
    lateinit var view: PrevMatchView

    @Mock
    lateinit var presenterTest: PrevMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenterTest = PrevMatchPresenter(view)
    }

    @Test
    fun getTeamList() {
        view.showLoading()
        verify(view).showLoading()
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(Match::class.java, object : ParsedRequestListener<Match> {
                    override fun onResponse(response: Match?) {
                        view.showPrevMatchList(response?.events)
                        verify(view).showPrevMatchList(response?.events)
                        view.hideLoading()
                        verify(view).hideLoading()
                        Assert.assertEquals(response!!::class.java,Match::class.java)
                    }

                    override fun onError(anError: ANError?) {
                        view.hideLoading()
                        verify(view).hideLoading()
                    }
                })
    }
}