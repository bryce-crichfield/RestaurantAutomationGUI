package org.eleven
package entities.status

import resource.ColorPalette._

import javafx.scene.paint.Color

trait Status {
    val color: Color
    def ID: String
}

trait OrderStatus extends Status
object OrderStatus {
    def OrderStatus(s: String): OrderStatus = s match {
        case "PLACED" => PLACED
        case "KITCHEN_ACCEPTED" => KITCHEN_ACCEPTED
        case "KITCHEN_COMPLETED" => KITCHEN_COMPLETED
        case "ORDER_DELIVERED" => ORDER_DELIVERED
        case "ORDER_COMPLETED" => ORDER_COMPLETED
        case _ => ERROR_STATUS_ORDER
    }
}
case object PLACED extends OrderStatus {
    val color: Color = Yellow100
    def ID: String = "PLACED"
}
case object KITCHEN_ACCEPTED extends OrderStatus {
    val color: Color = Yellow300
    def ID: String = "KITCHEN_ACCEPTED"
}
case object KITCHEN_COMPLETED extends OrderStatus {
    val color: Color = Yellow500
    def ID: String = "KITCHEN_COMPLETED"
}
case object ORDER_DELIVERED extends OrderStatus {
    val color: Color = Green100
    def ID: String = "ORDER_DELIVERED"
}
case object ORDER_COMPLETED extends OrderStatus {
    val color: Color = Green300
    def ID: String = "ORDER_COMPLETED"
}
case object ERROR_STATUS_ORDER extends OrderStatus {
    val color: Color = Red300
    def ID: String = "ERROR_STATUS_ORDER"
}

trait GuestStatus extends Status
object GuestStatus {
    def GuestStatus(s: String): GuestStatus = s match {
        case "LOBBIED" => LOBBIED
        case "SEATED" => SEATED
        case "BILLED" => BILLED
        case _ => ERROR_STATUS_GUEST
    }
}
case object LOBBIED extends GuestStatus {
    val color: Color = Yellow300
    def ID: String = "LOBBIED"
}
case object SEATED extends GuestStatus {
    val color: Color = Green100
    def ID: String = "SEATED"
}
case object BILLED extends GuestStatus {
    val color: Color = Green300
    def ID: String = "BILLED"
}
case object ERROR_STATUS_GUEST extends GuestStatus {
    val color: Color = Red500
    def ID: String = "ERROR_STATUS_GUEST"
}