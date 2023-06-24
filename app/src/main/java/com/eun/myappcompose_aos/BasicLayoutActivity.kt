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

class BasicLayoutActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var isFavCard by rememberSaveable {  // isFavCard 는 Boolean 값이다 remeberSaveable : 화면 회전시에도 상태 저장
                mutableStateOf(false)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {

                CardFavorite(  // ImageCard 함수 사용
                    mCardModifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(0.7f)
                        .padding(10.dp)
                        .align(Alignment.Center),  //Box 내부 Child 위치를 지정 - Center
                    mBoxModifier = Modifier
                        .background(colorResource(id = R.color.teal_200))
                        .padding(10.dp),
                    mPainter = painterResource(id = R.drawable.img_mint),
                    mIsFavCard = isFavCard
                ) { paramFavCard ->
                    isFavCard = paramFavCard
                }

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
 * @param mIsFavCard     IconButton 레이아웃의 색상 활성화 여부
 * @param onTabFavCard     IconButton 레이아웃의 onClick 콜백
 */
@Composable
fun CardFavorite(
    mCardModifier: Modifier,
    mBoxModifier: Modifier,
    mPainter: Painter,
    mIsFavCard: Boolean,
    onTabFavCard: (Boolean) -> Unit
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
                        onTabFavCard(!mIsFavCard)
                    }
                ) {

                    /**
                     * Icon 레이아웃
                     */
                    Icon(
                        imageVector = if(mIsFavCard) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite card btn",
                        tint = Color.Red
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun BasicLayoutPreview() {

    var isFavCard  = true

    Box(
        modifier = Modifier
            .fillMaxWidth()  // 가로길이 꽉차게
            .fillMaxHeight()  // 세로길이 꽉차게
            .fillMaxSize(),  // 가로세로 꽉차게
    ) {

        CardFavorite(  // ImageCard 함수 사용
            mCardModifier = Modifier
                .height(250.dp)
                .fillMaxWidth(0.7f)
                .padding(10.dp)
                .align(Alignment.Center),  //Box 내부 Child 위치를 지정 - Center
            mBoxModifier = Modifier
                .background(colorResource(id = R.color.teal_200))
                .padding(10.dp),
            mPainter = painterResource(id = R.drawable.img_mint),
            mIsFavCard = isFavCard
        ) { paramFavCard ->
            isFavCard = paramFavCard
        }

    }
}
