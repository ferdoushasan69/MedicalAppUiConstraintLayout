package com.example.medicalappuiconstraintlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

import kotlin.random.Random

@Composable
fun MedicalHomeScreenUi() {
    val configuration = LocalConfiguration.current.screenWidthDp
    ConstraintLayout(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        val (
            gradientBackGround,
            profileImg, notification,
            greetingMessage, askingMessage,
            urgentCare, doctorImg,
            bottomSheetCard, ourServiceText,
            consultationIcon, medicinesIcon, ambulanceIcon,
            consultationText, medicinesText, ambulanceText


        ) = createRefs()

        val horizontalGuideline = createGuidelineFromTop(.45f)
        Image(
            painter = painterResource(id = R.drawable.gradient_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .alpha(.8f)
                .constrainAs(gradientBackGround) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(horizontalGuideline)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }

        )

        val topGuideline = createGuidelineFromTop(.05f)
        val startGuideline = createGuidelineFromStart(.04f)
        val endGuideline = createGuidelineFromEnd(.04f)

        val imageSize = (configuration * .12).dp
        val dynamicSize = imageSize * 0.75f

        Image(
            painterResource(R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(profileImg) {
                    top.linkTo(topGuideline)
                    start.linkTo(startGuideline)
                }
                .clip(CircleShape)
                .size(42.dp)
        )

        Image(
            painterResource(R.drawable.notification),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(notification) {
                    top.linkTo(topGuideline)
                    end.linkTo(endGuideline)
                    bottom.linkTo(profileImg.bottom)
                }
        )

        val greetingBarrier = createEndBarrier(
            greetingMessage, askingMessage
        )

        val userName = if (Random.nextInt(0, 10) % 2 == 0) "Cengiz" else "Cengiz TORU"

        val greetMessageWithUser = "Welcome ! \n$userName"
        Text(
            greetMessageWithUser,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.constrainAs(greetingMessage) {
                top.linkTo(profileImg.bottom, margin = dynamicSize)
                start.linkTo(startGuideline)

            }
        )
        Text("How is it going today ?",
            color = Color.Gray,
            modifier = Modifier.constrainAs(askingMessage) {
                top.linkTo(greetingMessage.bottom, margin = 15.dp)
                start.linkTo(greetingMessage.start)
            }
        )
        Image(
            painterResource(R.drawable.urgent_care),
            contentDescription = null,
            modifier = Modifier.constrainAs(urgentCare) {
                top.linkTo(askingMessage.bottom, margin = 32.dp)
                start.linkTo(askingMessage.start)
                end.linkTo(askingMessage.end)
                bottom.linkTo(horizontalGuideline)
            }
        )
        Image(
            painterResource(R.drawable.doctor),
            contentDescription = null,
            modifier = Modifier.constrainAs(doctorImg) {
                top.linkTo(greetingMessage.top)
                start.linkTo(greetingMessage.end)
                end.linkTo(notification.end)
                bottom.linkTo(horizontalGuideline)
                width = Dimension.fillToConstraints
            }
        )

        Card(
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            modifier = Modifier.constrainAs(bottomSheetCard) {
                top.linkTo(horizontalGuideline, margin = (-4).dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            colors = CardDefaults.cardColors(Color.White)
        ) {}
        Text("Our Service",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.constrainAs(ourServiceText) {
                start.linkTo(startGuideline, 15.dp)
                top.linkTo(horizontalGuideline, 24.dp)
            }
        )

        createHorizontalChain(
            consultationIcon,
            medicinesIcon,
            ambulanceIcon,
            chainStyle = ChainStyle.Spread
        )
        Image(
            painterResource(R.drawable.consultation_icon),
            contentDescription = null,
            modifier = Modifier.constrainAs(consultationIcon) {
                top.linkTo(ourServiceText.bottom, margin = 15.dp)
                start.linkTo(startGuideline)
            }
        )

        Image(
            painterResource(R.drawable.medicines_icon),
            contentDescription = null,
            modifier = Modifier.constrainAs(medicinesIcon) {
                top.linkTo(consultationIcon.top)
                start.linkTo(consultationIcon.end)
                bottom.linkTo(consultationIcon.bottom)
            }
        )
        Image(
            painterResource(R.drawable.ambulance_icon),
            contentDescription = null,
            modifier = Modifier.constrainAs(ambulanceIcon) {
                top.linkTo(medicinesIcon.top)
                start.linkTo(medicinesIcon.end)
                bottom.linkTo(medicinesIcon.bottom)

            }
        )

        Text("Consultation",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(consultationText) {
                top.linkTo(consultationIcon.bottom, margin = 12.dp)
                end.linkTo(consultationIcon.end)
                start.linkTo(consultationIcon.start)
            }
        )
        Text("Medicines",
            style = MaterialTheme.typography.titleSmall,
            
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(medicinesText) {
                top.linkTo(medicinesIcon.bottom, margin = 12.dp)
                end.linkTo(medicinesIcon.end)
                start.linkTo(medicinesIcon.start)
            }
        )
        Text("Ambulance",
            style = MaterialTheme.typography.titleSmall,

            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(ambulanceText) {
                top.linkTo(ambulanceIcon.bottom, margin = 12.dp)
                end.linkTo(ambulanceIcon.end)
                start.linkTo(ambulanceIcon.start)
            }
        )

        val (appointmentText, seAllText,
            appointmentItemCard, appointmentDateText,
            appointDateIcon, appointmentDate, appointmentOptions,
            appointmentDivider, appointmentDoctorProfileImage,
            appointmentDoctorName, appointmentDoctorBranch) = createRefs()
        Text(
            "Appointment",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(appointmentText) {
                top.linkTo(consultationText.bottom, margin = 30.dp)
                start.linkTo(startGuideline)
            }
        )
        Text(
            "See All",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Blue,
            modifier = Modifier.constrainAs(seAllText) {
                top.linkTo(appointmentText.top)
                end.linkTo(endGuideline)
            }
        )

        Card(
            elevation = CardDefaults.elevatedCardElevation(2.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier.constrainAs(appointmentItemCard) {
                top.linkTo(appointmentText.bottom, margin = 15.dp)
                start.linkTo(appointmentText.start)
                end.linkTo(endGuideline)
                bottom.linkTo(appointmentDoctorProfileImage.bottom, margin = (-16).dp)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .wrapContentSize(Alignment.TopStart)
                    .fillMaxHeight()
                    .background(Color(0xAA4D94FF))

            ){
                Spacer(Modifier.width(8.dp))
            }
        }
        Text("Appointment Date",
            modifier = Modifier.constrainAs(appointmentDateText){
                top.linkTo(appointmentItemCard.top, margin = 16.dp)
                start.linkTo(appointmentItemCard.start, margin = 24.dp)
            })
        Image(
            painterResource(R.drawable.time),
            contentDescription = null,
            modifier = Modifier.constrainAs(appointDateIcon){
                top.linkTo(appointmentDateText.bottom, margin = 8.dp)
                start.linkTo(appointmentDateText.start)
            }
        )
        Text("Fri Jan 31 | 8:00 - 8:30 AM",
            modifier = Modifier.constrainAs(appointmentDate){
                top.linkTo(appointDateIcon.top)
                start.linkTo(appointDateIcon.end, margin = 4.dp)
                bottom.linkTo(appointDateIcon.bottom)
            })

        Image(
            painterResource(R.drawable.options),
            contentDescription = null,
            modifier = Modifier.constrainAs(appointmentOptions){
                top.linkTo(appointmentDateText.top)
                end.linkTo(appointmentItemCard.end,8.dp)
                bottom.linkTo(appointmentDate.bottom)
            }
        )
        HorizontalDivider(
            color = Color.Gray,
            thickness = (0.5f).dp,
            modifier = Modifier.constrainAs(appointmentDivider){
            top.linkTo(appointmentDate.bottom, margin = 16.dp)
            start.linkTo(appointDateIcon.start)
                end.linkTo(appointmentOptions.end)
                width = Dimension.fillToConstraints
        }.alpha(.5f))
        Image(
            painterResource(R.drawable.appointment_doctor),
            contentDescription = null,
            modifier = Modifier.constrainAs(appointmentDoctorProfileImage){
                top.linkTo(appointmentDivider.bottom,16.dp)
                start.linkTo(appointmentDivider.start)
            }
        )
        Text(
            "DDr. Rıdvan TORU",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(appointmentDoctorName){
                top.linkTo(appointmentDoctorProfileImage.top)
                start.linkTo(appointmentDoctorProfileImage.end,12.dp)
            }
        )
        Text(
            "orthopedic",
            color = Color.Gray,
            modifier = Modifier.constrainAs(appointmentDoctorBranch){
                top.linkTo(appointmentDoctorName.bottom, margin = 2.dp)
                start.linkTo(appointmentDoctorName.start)
            }
        )

    }
}

@Preview(showSystemUi = true)
@Composable
private fun MedicalHomeScreenUiPreview() {
    MedicalHomeScreenUi()

}
