package com.sennohananto.footballmatchschedule.detailMatch

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.sennohananto.footballmatchschedule.matches.detailMatch.DetailMatchPresenter
import com.sennohananto.footballmatchschedule.matches.detailMatch.DetailMatchView
import org.json.JSONObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {

    @Mock
    private
    lateinit var view: DetailMatchView

    @Mock
    lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view)
    }

    @Test
    fun getTeamDetail() {
        view.showLoading()
        verify(view).showLoading()
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php")
                .addQueryParameter("id","134301")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        Assert.assertEquals(1, response?.getJSONArray("teams")?.length())
                        view.hideLoading()
                        verify(view).hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        view.hideLoading()
                        verify(view).hideLoading()
                    }
                })
    }
}