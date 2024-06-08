package dev.mysticmaster.tamngon247.ui.screen.home.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.mysticmaster.tamngon247.ui.theme.BackgroundColor
import dev.mysticmaster.tamngon247.ui.theme.ItemColor

data class ListItem(val title: String, val description: String, val price: String)

@Composable
fun AdminHomeScreen() {

    val itemsList = listOf(
        ListItem("Đơn hàng CT2E22E", "Từ chối", "100.000 đ"),
        ListItem("Đơn hàng CT2E2206", "Chấp nhận", "500.000 đ"),
        ListItem("Đơn hàng CT2E23E", "Từ chối", "100.800 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),

        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "Today:23-09-2024 ",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            modifier = Modifier.padding(top = 16.dp)

        )

        Text(
            text = "Số lượng đơn: 1",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
        )


        Text(
            text = "Doanh thu: 500.000 đ",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
        )


        LazyColumn(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .fillMaxSize()
        )
        {
            items(itemsList) { item ->
                ListItemView(item = item)
            }
        }
    }


}

@Composable
fun ListItemView(item: ListItem) {

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .background(ItemColor)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
                Text(
                    text = "||",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable {

                        }
                )
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(end = 10.dp, bottom = 10.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Trạng Thái",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Red,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }

    }
}