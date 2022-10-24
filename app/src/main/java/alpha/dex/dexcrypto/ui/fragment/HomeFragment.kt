package alpha.dex.dexcrypto.ui.fragment

import alpha.dex.dexcrypto.adapter.TopGainLoseAdapter
import alpha.dex.dexcrypto.adapter.TopMarketAdapter
import alpha.dex.dexcrypto.api.ApiInterface
import alpha.dex.dexcrypto.api.ApiUtilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alpha.dex.dexcrypto.databinding.FragmentHomeBinding
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        getTopCurrencyList()
        setTabLayout()

        return binding.root
    }

    private fun setTabLayout(){
        val adapter = TopGainLoseAdapter(this)
        binding.contentViewPager.adapter = adapter

        binding.contentViewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0){
                    binding.topGainIndicator.visibility = VISIBLE
                    binding.topLoseIndicator.visibility = GONE
                } else{
                    binding.topGainIndicator.visibility = GONE
                    binding.topLoseIndicator.visibility = VISIBLE
                }
            }
        })

        TabLayoutMediator(binding.tabLayout, binding.contentViewPager){
            tab, position ->
            val title = if (position == 0){
                "Top Gainers"
            } else {
                "Top Losers"
            }
            tab.text = title
        }.attach()

    }


    private fun getTopCurrencyList() {
        lifecycleScope.launch(Dispatchers.IO) {
            val res = ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()

            withContext(Dispatchers.Main) {
                binding.topCurrencyRecyclerView.adapter = TopMarketAdapter(requireContext(),res.body()!!.data.cryptoCurrencyList)
            }
        }
    }
}