package com.example.project8_classc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.project8_classc.ui.home.screen.DestinasiHome
import com.example.project8_classc.ui.home.screen.HomeScreen
import com.example.project8_classc.ui.kontak.screen.DestinasiEntry
import com.example.project8_classc.ui.kontak.screen.DetailsDestination
import com.example.project8_classc.ui.kontak.screen.DetailsScreen
import com.example.project8_classc.ui.kontak.screen.EditDestination
import com.example.project8_classc.ui.kontak.screen.EntryKontakScreen
import com.example.project8_classc.ui.kontak.screen.ItemEditScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
       composable(DestinasiHome.route){
           HomeScreen(navigateToItemEntry = {
               navController.navigate(DestinasiEntry.route)
           },
               onDetailClick = {itemId ->
                   navController.navigate("${DetailsDestination.route}/$itemId")
                   println(itemId)
               }
           )
       }
        composable(
            DestinasiEntry.route
        ){
            EntryKontakScreen(navigateBack = {
                navController.navigate(
                    DestinasiHome.route
                ){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }

        composable(
            EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.kontakId){
                type = NavType.IntType
            })
        ){
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
            )
        }

        composable(
            DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.kontakId) {
                type = NavType.IntType
            })
        ){backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(DetailsDestination.kontakId)
            itemId?.let {
                DetailsScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { itemId ->
                        navController.navigate("${EditDestination.route}/$itemId")
                        println(itemId)
                    }
                )
            }
        }

        composable(EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.kontakId){
                type = NavType.IntType
            })
        ){
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = {
                    navController.navigate(DestinasiHome.route){
                        popUpTo(DestinasiHome.route){
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
            )
        }
    }
}