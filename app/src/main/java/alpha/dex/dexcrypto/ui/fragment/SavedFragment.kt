package alpha.dex.dexcrypto.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alpha.dex.dexcrypto.R
import alpha.dex.dexcrypto.adapter.MarketAdapter
import alpha.dex.dexcrypto.api.ApiInterface
import alpha.dex.dexcrypto.api.ApiUtilities
import alpha.dex.dexcrypto.databinding.FragmentHomeBinding
import alpha.dex.dexcrypto.databinding.FragmentSavedBinding
import alpha.dex.dexcrypto.model.CryptoCurrency
import android.content.Context
import android.view.View.GONE
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedFragment : Fragment() {


    private lateinit var binding: FragmentSavedBinding
    private lateinit var savedList: ArrayList<String>
    private lateinit var savedListItem: ArrayList<CryptoCurrency>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(layoutInflater)

        readData()
        lifecycleScope.launch(Dispatchers.IO){
            val res = ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()

            if (res.body()!= null){
                withContext(Dispatchers.Main){
                    savedListItem = ArrayList()
                    savedListItem.clear()

                    for (savedData in savedList){
                        for (item in res.body()!!.data.cryptoCurrencyList){
                            if (savedData == item.symbol){
                                savedListItem.add(item)
                            }
                        }
                    }
                    binding.spinKitView.visibility = GONE
                    binding.watchlistRecyclerView.adapter = MarketAdapter(requireContext(), savedListItem, "savedFragment")
                }
            }
        }

        return binding.root
    }
    private fun readData() {
        val sharedPreferences =
            requireContext().getSharedPreferences("savedList", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("savedList", ArrayList<String>().toString())

        val type = object : TypeToken<ArrayList<String>>() {}.type

        savedList = gson.fromJson(json, type)
    }
}