package alpha.dex.dexcrypto.model

data class Quote(
    val dominance: Double,
    val fullyDilluttedMarketCap: Double,
    val lastUpdated: String,
    val marketCap: Double,
    val marketCapByTotalSupply: Double,
    val name: String,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val percentChange30d: Double,
    val percentChange60d: Double,
    val percentChange7d: Double,
    val percentChange90d: Double,
    val price: Double,
    val turnover: Double,
    val tvl: Double,
    val volume24h: Double,
    val ytdPriceChangePercentage: Double
)