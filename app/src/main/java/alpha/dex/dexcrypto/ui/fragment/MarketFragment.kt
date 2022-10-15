package alpha.dex.dexcrypto.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alpha.dex.dexcrypto.R
import alpha.dex.dexcrypto.databinding.FragmentHomeBinding
import alpha.dex.dexcrypto.databinding.FragmentMarketBinding

class MarketFragment : Fragment() {

    companion object {
        const val TAG = "FragmentStats"
    }

    private var _binding: FragmentMarketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketBinding.inflate(
            inflater, container, /* attachToParent */ false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMarketBinding.bind(view)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}