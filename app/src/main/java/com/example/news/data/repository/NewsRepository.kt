package com.example.news.data.repository

import com.example.news.data.model.News

class NewsRepository {
    fun getSampleNews(): List<News> {
        return listOf(
            News(
                1,
                "2025-01-09",
                "New images of Mercury captured by UK spacecraft",
                "BepiColombo, a UK-built spacecraft launched in 2018, captured new images of Mercury during its sixth and final flyby, passing just 295 km above the planet’s surface. The spacecraft provided views of Mercury's north poles lit by sunlight. It aims to study the planet's composition and check for water in its deep craters. Special shielding is used to protect the spacecraft from the sun's intense heat. After this flyby, the spacecraft will separate from its modules and begin its main mission, entering Mercury's orbit in 2026. BepiColombo needed nine flybys to adjust its speed for orbital capture.",
                "https://ichef.bbci.co.uk/news/1024/cpsprodpb/9791/live/6def87e0-ce98-11ef-a30d-93fd67dd3d91.jpg.webp"
                ),
            News(
                2,
                "2025-01-10",
                "Brazil Gives Meta 72 Hours to Explain Changes to Fact-Checking Program",
                "Brazil’s government has given Meta 72 hours to explain recent changes to its fact-checking program after it scrapped the U.S. initiative and relaxed restrictions on topics like immigration and gender identity. Solicitor General Jorge Messias expressed concern, calling the changes inconsistent and detrimental to Brazilian society. President Luiz Inácio Lula da Silva criticized the alterations as \"extremely serious\" and has called for further discussions. Meta has not responded publicly, and it is unclear what action Brazil will take after the deadline expires. CEO Mark Zuckerberg stated that the changes were due to \"too many mistakes and too much censorship.\"",
                "https://th-i.thgim.com/public/incoming/n7e7yr/article69087714.ece/alternates/LANDSCAPE_1200/2025-01-09T203618Z_377012743_RC290Y9HCM7R_RTRMADP_3_META-OPENAI-SILVERMAN-INFRINGEMENT.JPG"
            ),
            News(
                3,
                "2025-01-10",
                "Blinkit expands electronics portfolio; to now deliver laptops, monitors, printers in 10 minutes",
                "Zomato-owned Blinkit has expanded its offerings to include electronics like laptops, monitors, and printers, delivering them in 10 minutes in cities such as Delhi NCR, Pune, Mumbai, Bengaluru, Kolkata, and Lucknow. CEO Albinder Dhindsa announced that the deliveries would be handled by Blinkit’s large order fleet, with more brands being added soon. This expansion positions Blinkit alongside competitors like Flipkart Minutes and Bigbasket’s BB Now. The company is shifting towards becoming a horizontal marketplace, diversifying into categories like fashion, beauty, and home décor. Blinkit also launched a 10-minute ambulance service in Gurugram. India’s quick commerce market is projected to grow significantly by 2030, with Blinkit capitalizing on this trend.",
                "https://www.newsx.com/wp-content/uploads/2025/01/IMG_0376.webp"
            )
        )
    }
}