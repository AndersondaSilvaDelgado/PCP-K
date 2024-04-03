package br.com.usinasantafe.pcpk.presenter.proprio.veiculo

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.usinasantafe.pcpk.presenter.proprio.veiculoseg.VeiculoSegProprioListFragment
import br.com.usinasantafe.pcpk.utils.TypeAddEquip
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class VeiculoProprioFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `Test of Open Fragment VeiculoProprioFragment`() {
        launchFragmentInHiltContainer<VeiculoProprioFragment>{
            val bundle = Bundle()
            bundle.putInt(VeiculoProprioFragment.TYPE_ADD_EQUIP_VEICULO_PROPRIO, TypeAddEquip.ADDVEICULO.ordinal)
            bundle.putInt(VeiculoProprioFragment.POS_VEICULO_PROPRIO, 0)
            this.parentFragmentManager.setFragmentResult(VeiculoProprioFragment.REQUEST_KEY_VEICULO_PROPRIO, bundle)
//            this.bind.invoke(this.view!!).editTextPadrao.setText("19759")
//            this.handleCheckMatric(false)
//            Assert.assertNotEquals(this.alertDialog, null)
//            Assert.assertTrue(this.alertDialog!!.isShowing)
//            val messageTextView = this.alertDialog!!.findViewById<TextView>(R.id.message)
//            val msg = messageTextView!!.text.toString()
//            Assert.assertTrue(msg.contains("MATRICULA DO COLABORADOR"))
        }
    }
}