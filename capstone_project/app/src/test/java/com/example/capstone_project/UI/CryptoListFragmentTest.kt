package com.example.capstoneproject.UI

import com.example.capstoneproject.presentation.ui.adapter.AvailableBookAdapter
import com.example.capstoneproject.presentation.ui.view.CriptoListFragment
import com.example.capstoneproject.presentation.ui.viewmodel.MainActivityViewModel
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.After

class CryptoListFragmentTest {

    private val mockCryptoViewModel = mockk<MainActivityViewModel>(relaxed = true)
    private val mockCryptoAdapter = mockk<AvailableBookAdapter>(relaxed = true)

    @After
    fun tearDown() {
        clearAllMocks()
    }

    /* @Test
     fun `test onViewCreated`(){
         val target = spyk(createFragmentInstance())
         every { target.initWS() } returns Unit
         target.onViewCreated(mockk(relaxed = true), mockk(relaxed = true))
         verify (exactly = 1) {target.initWS()}
     }
     @Test
     fun `test initWS`() {
         val target = spyk(createFragmentInstance())
         val escenario = launchFragment { target }
         escenario.moveToState(Lifecycle.State.RESUMED)
         escenario.onFragment{ fragment ->
             verify { fragment.initWS() }
         }
         escenario.moveToState(Lifecycle.State.DESTROYED)
     }*/

    private fun createFragmentInstance(): CriptoListFragment {
        return CriptoListFragment()
    }
}