package dev.mysticmaster.tamngon247.util

sealed class Route(val screen: String) {
    data object Welcome: Route("Welcome")
    data object AdminLogin: Route("AdminLogin")
    data object CustomerLogin: Route("CustomerLogin")
    data object AdminBottomAppBar: Route("AdminBottomAppBar")
    data object CustomerBottomAppBar: Route("CustomerBottomAppBar")

    // Admin
    data object AdminHome: Route("AdminHome")
    data object AdminStatistics: Route("AdminStatistics")
    data object AdminProductManager: Route("AdminProductManager")
    data object AdminExtraFeature: Route("AdminExtraFeature")
    data object CategoryManager:Route("CategoryManager")
    data object DishManager:Route("DishManager")
}