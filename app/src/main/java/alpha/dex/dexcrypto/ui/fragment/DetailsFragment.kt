package alpha.dex.dexcrypto.ui.fragment

import alpha.dex.dexcrypto.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alpha.dex.dexcrypto.databinding.FragmentDetailsBinding
import alpha.dex.dexcrypto.model.CryptoCurrency
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide


class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

    private val item: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDetailsBinding.inflate(layoutInflater)

        val data: CryptoCurrency = item.detail!!

        setUpDetails(data)
        loadChart(data)

        setButtonOnclick(data)

        return binding.root
    }

    private fun setButtonOnclick(item: CryptoCurrency) {
        val oneMonth = binding.button
        val oneWeek = binding.button1
        val oneDay = binding.button2
        val fourHour = binding.button3
        val oneHour = binding.button4
        val fifteenMin = binding.button5

        val clickListener = View.OnClickListener {
            when (it.id) {
                fifteenMin.id -> loadChartData(
                    it,
                    "15",
                    item,
                    oneDay,
                    oneMonth,
                    oneWeek,
                    fourHour,
                    oneHour
                )
                oneHour.id -> loadChartData(
                    it,
                    "1H",
                    item,
                    oneDay,
                    oneMonth,
                    oneWeek,
                    fourHour,
                    fifteenMin
                )
                fourHour.id -> loadChartData(
                    it,
                    "4H",
                    item,
                    oneDay,
                    oneMonth,
                    oneWeek,
                    fifteenMin,
                    oneHour
                )
                oneDay.id -> loadChartData(
                    it,
                    "D",
                    item,
                    fifteenMin,
                    oneMonth,
                    oneWeek,
                    fourHour,
                    oneHour
                )
                oneWeek.id -> loadChartData(
                    it,
                    "W",
                    item,
                    oneDay,
                    oneMonth,
                    fifteenMin,
                    fourHour,
                    oneHour
                )
                oneMonth.id -> loadChartData(
                    it,
                    "M",
                    item,
                    oneDay,
                    fifteenMin,
                    oneWeek,
                    fourHour,
                    oneHour
                )
            }
        }
        fifteenMin.setOnClickListener(clickListener)
        oneHour.setOnClickListener(clickListener)
        fourHour.setOnClickListener(clickListener)
        oneDay.setOnClickListener(clickListener)
        oneWeek.setOnClickListener(clickListener)
        oneMonth.setOnClickListener(clickListener)
    }

    private fun loadChartData(
        it: View?,
        s: String,
        item: CryptoCurrency,
        oneDay: AppCompatButton,
        oneMonth: AppCompatButton,
        oneWeek: AppCompatButton,
        fourHour: AppCompatButton,
        oneHour: AppCompatButton
    ) {
        disableButton(oneDay, oneMonth, oneWeek, fourHour, oneHour)
        it!!.setBackgroundResource(R.drawable.active_button)
        binding.detaillChartWebView.settings.javaScriptEnabled = true
        binding.detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        binding.detaillChartWebView.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + item.symbol.toString() + "USD&interval=" + s + "&hidesidetoolbar=1&hidetoptoolbar=1&symbo ledit=1&saveimage=1&toolbarbg=F1F3F6&studi es=[]&hideideas=1&theme=Dark&style=1&time zone=Etc/UTC&studies_overrides={} &overrides={}&enabled_features=[]&disabled_features=[]&loc ale=en&utmsource=coinmarketcap.com&utm medium=widget&utm_campaign=chart&utm_te rm=BTCUSDT")
    }

    private fun disableButton(
        oneDay: AppCompatButton,
        oneMonth: AppCompatButton,
        oneWeek: AppCompatButton,
        fourHour: AppCompatButton,
        oneHour: AppCompatButton
    ) {
            oneDay.background = null
            oneMonth.background = null
            oneWeek.background = null
            fourHour.background = null
            oneHour.background = null
    }

    private fun loadChart(item: CryptoCurrency) {
        binding.detaillChartWebView.settings.javaScriptEnabled = true
        binding.detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        binding.detaillChartWebView.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + item.symbol.toString() + "USD&interval=D&hidesidetoolbar=1&hidetoptoolbar=1&symbo ledit=1&saveimage=1&toolbarbg=F1F3F6&studi es=[]&hideideas=1&theme=Dark&style=1&time zone=Etc/UTC&studies_overrides={} &overrides={}&enabled_features=[]&disabled_features=[]&loc ale=en&utmsource=coinmarketcap.com&utm medium=widget&utm_campaign=chart&utm_te rm=BTCUSDT")
    }

    private fun setUpDetails(data: CryptoCurrency) {
        binding.detailSymbolTextView.text = data.symbol

        Glide.with(requireContext()).load(
            "https://s2.coinmarketcap.com/static/img/coins/64x64/" + data.id + ".png"
        ).thumbnail(Glide.with(requireContext()).load(R.drawable.spinner))
            .into(binding.detailImageView)

        binding.detailPriceTextView.text =
            "$ ${String.format("%.4f", data.quotes[0].price)}"


        if (data.quotes!![0].percentChange24h > 0) {
            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.green))
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_up)
            binding.detailChangeTextView.text =
                "+${String.format("%.02f", data.quotes[0].percentChange24h)} %"
        } else {
            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.red))
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_down)
            binding.detailChangeTextView.text =
                "${String.format("%.02f", data.quotes[0].percentChange24h)} %"
        }

    }

}