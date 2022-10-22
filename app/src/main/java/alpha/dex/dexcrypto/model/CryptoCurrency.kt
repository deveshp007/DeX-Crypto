package alpha.dex.dexcrypto.model

data class CryptoCurrency(
    val auditInfoList: List<AuditInfo>,
    val circulatingSupply: Double,
    val cmcRank: Int,
    val dateAdded: String,
    val id: Int,
    val isActive: Int,
    val isAudited: Boolean,
    val lastUpdated: String,
    val marketPairCount: Int,
    val maxSupply: Double,
    val name: String,
    val platform: Platform,
    val quotes: List<Quote>,
    val selfReportedCirculatingSupply: Double,
    val slug: String,
    val symbol: String,
    val tags: List<String>,
    val totalSupply: Double
)