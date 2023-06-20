package com.eun.myappcompose_aos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ImgCardActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//        var isFavorite: MutableState<Boolean> = remember {  // isFavorite 상태를 기억해준다 (remeber)
            var isFavorite by rememberSaveable {  // isFavorite 는 Boolean 값이다 remeberSaveable : 화면 회전시에도 상태 저장
                mutableStateOf(false)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {

                ImageCard(  // ImageCard 함수 사용
                    mCardModifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(0.7f)
                        .padding(10.dp)
                        .align(Alignment.TopStart),  //Box 내부 Child 위치를 지정 - TopStart
                    mBoxModifier = Modifier
                        .background(colorResource(id = R.color.yellow_200))
                        .padding(10.dp),
                    mPainter = painterResource(id = R.drawable.img_yellow),
                    mIsFavorite = isFavorite
                ) { paramFavorite ->
                    isFavorite = paramFavorite
                }

                ImageCard(  // ImageCard 함수 사용
                    mCardModifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(0.7f)
                        .padding(10.dp)
                        .align(Alignment.Center),  //Box 내부 Child 위치를 지정 - Center
                    mBoxModifier = Modifier
                        .background(colorResource(id = R.color.teal_200))
                        .padding(10.dp),
                    mPainter = painterResource(id = R.drawable.img_mint),
                    mIsFavorite = isFavorite
                ) { paramFavorite ->
                    isFavorite = paramFavorite
                }

                ImageCard(  // ImageCard 함수 사용
                    mCardModifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(0.7f)
                        .padding(10.dp)
                        .align(Alignment.BottomEnd),  //Box 내부 Child 위치를 지정 - BottomEnd
                    mBoxModifier = Modifier
                        .background(colorResource(id = R.color.purple_200))
                        .padding(10.dp),
                    mPainter = painterResource(id = R.drawable.img_purple),
                    mIsFavorite = isFavorite
                ) { paramFavorite ->
                    isFavorite = paramFavorite
                }

                TestStateTxt()

            }
        }
    }

}


/**
 * ImageCard 컴포저블 함수
 *
 * @param mCardModifier     Card 레이아웃의 modifier
 * @param mBoxModifier     Box 레이아웃의 modifier
 * @param mPainter     Image 레이아웃의 painter
 * @param mIsFavorite     IconButton 레이아웃의 색상 활성화 여부
 * @param onTabFavorite     IconButton 레이아웃의 onClick 콜백
 */
@Composable
fun ImageCard(
    mCardModifier: Modifier,
    mBoxModifier: Modifier,
    mPainter: Painter,
    mIsFavorite: Boolean,
    onTabFavorite: (Boolean) -> Unit
) {

    /**
     * Card 레이아웃
     *   - CareView 와 비슷
     */
    Card(
        modifier = mCardModifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
    ) {

        /**
         * Box 레이아웃
         *   - FrameLayout 과 비슷
         *   - 레이아웃을 겹쳐서 사용 가능
         */
        Box(
            modifier = mBoxModifier,
        ) {

            /**
             * Image 레이아웃
             */
            Image(
                painter = mPainter,
                contentDescription = "poster",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {

                /**
                 * IconButton 레이아웃
                 */
                IconButton(onClick = {
                        onTabFavorite(!mIsFavorite)
                    }
                ) {

                    /**
                     * Icon 레이아웃
                     */
                    Icon(
                        imageVector = if(mIsFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite btn",
                        tint = Color.Red
                    )
                }
            }
        }
    }

}


@Composable
fun TestStateTxt() {
    // *** 1. val mutableState = remember { mutableStateOf(default) }
    val txtState1 = remember {mutableStateOf("")}
    TextField(
        value = txtState1.value,
        onValueChange = { txtValue -> txtState1.value = txtValue }
    )

    // *** 2. var value by remember { mutableStateOf(default) }
    var txtState2 by remember {mutableStateOf("")}
    TextField(
        value = txtState2,
        onValueChange = { txtValue -> txtState2 = txtValue }
    )

    // *** 3. val (value, setValue) = remember { mutableStateOf(default) }
    val (txtState3, setTxtState) = remember {mutableStateOf("")}
    TextField(
        value = txtState3,
        onValueChange = setTxtState
    )
}


@Preview(showBackground = true)
@Composable
fun ImgCardPreview() {

    var isFavorite  = true

    Box(
        modifier = Modifier
            .fillMaxWidth()  // 가로길이 꽉차게
            .fillMaxHeight()  // 세로길이 꽉차게
            .fillMaxSize(),  // 가로세로 꽉차게
    ) {

        ImageCard(  // ImageCard 함수 사용
            mCardModifier = Modifier
                .height(250.dp)
                .fillMaxWidth(0.7f)
                .padding(10.dp)
                .align(Alignment.TopStart),  //Box 내부 Child 위치를 지정 - TopStart
            mBoxModifier = Modifier
                .background(colorResource(id = R.color.yellow_200))
                .padding(10.dp),
            mPainter = painterResource(id = R.drawable.img_yellow),
            mIsFavorite = isFavorite
        ) { paramFavorite ->
            isFavorite = paramFavorite
        }

        ImageCard(  // ImageCard 함수 사용
            mCardModifier = Modifier
                .height(250.dp)
                .fillMaxWidth(0.7f)
                .padding(10.dp)
                .align(Alignment.Center),  //Box 내부 Child 위치를 지정 - Center
            mBoxModifier = Modifier
                .background(colorResource(id = R.color.teal_200))
                .padding(10.dp),
            mPainter = painterResource(id = R.drawable.img_mint),
            mIsFavorite = isFavorite
        ) { paramFavorite ->
            isFavorite = paramFavorite
        }

        ImageCard(  // ImageCard 함수 사용
            mCardModifier = Modifier
                .height(250.dp)
                .fillMaxWidth(0.7f)
                .padding(10.dp)
                .align(Alignment.BottomEnd),  //Box 내부 Child 위치를 지정 - BottomEnd
            mBoxModifier = Modifier
                .background(colorResource(id = R.color.purple_200))
                .padding(10.dp),
            mPainter = painterResource(id = R.drawable.img_purple),
            mIsFavorite = isFavorite
        ) { paramFavorite ->
            isFavorite = paramFavorite
        }

        TestStateTxt()

    }
}
