package com.r76127011.setcardgame.model

data class SetCard(val number: SetCardNumber, val color: SetCardColor, val shape: SetCardShape, val shading: SetCardShading)

enum class SetCardNumber (val value: Int) {
    ONE (1),
    TWO (2),
    THREE (3)
}

enum class SetCardColor (val value: String) {
    RED ("red"),
    GREEN ("green"),
    BLUE ("blue")
}

enum class SetCardShape (val value: String) {
    DIAMOND ("diamond"),
    SQUIGGLE ("squiggle"),
    OVAL ("oval")
}

enum class SetCardShading (val value: String) {
    SOLID ("solid"),
    STRIPED ("striped"),
    OPEN ("open")
}
