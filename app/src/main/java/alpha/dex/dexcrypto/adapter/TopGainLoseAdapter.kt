package alpha.dex.dexcrypto.adapter

import alpha.dex.dexcrypto.ui.fragment.TopGainLoseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TopGainLoseAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TopGainLoseFragment()
        val bundle = Bundle()

        bundle.putInt("position",position)
        fragment.arguments = bundle
        return fragment
    }

}