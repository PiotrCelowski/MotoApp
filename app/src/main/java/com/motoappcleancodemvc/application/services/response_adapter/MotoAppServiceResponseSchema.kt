package com.motoappcleancodemvc.application.services.response_adapter

data class MotoAppServiceResponseSchema(
    val code: String = "",
    val routes: List<Route> = listOf(),
    val uuid: String = "",
    val waypoints: List<Waypoint> = listOf()
)

data class Waypoint(
    val distance: Double = 0.0,
    val location: List<Double> = listOf(),
    val name: String = ""
)
data class Route(
    val distance: Double = 0.0,
    val duration: Double = 0.0,
    val geometry: String = "",
    val legs: List<Leg> = listOf(),
    val voiceLocale: String = "",
    val weight: Double = 0.0,
    val weight_name: String = ""
) {
    data class Leg(
        val admins: List<Admin> = listOf(),
        val distance: Double = 0.0,
        val duration: Double = 0.0,
        val steps: List<Step> = listOf(),
        val summary: String = "",
        val weight: Double = 0.0
    ) {
        data class Admin(
            val iso_3166_1: String = "",
            val iso_3166_1_alpha3: String = ""
        )

        data class Step(
            val bannerInstructions: List<BannerInstruction> = listOf(),
            val destinations: String = "",
            val distance: Double = 0.0,
            val driving_side: String = "",
            val duration: Double = 0.0,
            val geometry: String = "",
            val intersections: List<Intersection> = listOf(),
            val maneuver: Maneuver = Maneuver(),
            val mode: String = "",
            val name: String = "",
            val ref: String = "",
            val rotary_name: String = "",
            val voiceInstructions: List<VoiceInstruction> = listOf(),
            val weight: Double = 0.0
        ) {
            data class BannerInstruction(
                val distanceAlongGeometry: Double = 0.0,
                val primary: Primary = Primary(),
                val secondary: Any? = null,
                val sub: Sub = Sub()
            ) {
                data class Primary(
                    val components: List<Component> = listOf(),
                    val degrees: Int = 0,
                    val driving_side: String = "",
                    val modifier: String = "",
                    val text: String = "",
                    val type: String = ""
                ) {
                    data class Component(
                        val delimiter: Boolean = false,
                        val text: String = "",
                        val type: String = ""
                    )
                }

                data class Sub(
                    val components: List<Component> = listOf(),
                    val text: String = ""
                ) {
                    data class Component(
                        val active: Boolean = false,
                        val directions: List<String> = listOf(),
                        val text: String = "",
                        val type: String = ""
                    )
                }
            }

            data class Intersection(
                val admin_index: Int = 0,
                val bearings: List<Int> = listOf(),
                val classes: List<String> = listOf(),
                val duration: Double = 0.0,
                val entry: List<Boolean> = listOf(),
                val geometry_index: Int = 0,
                val `in`: Int = 0,
                val is_urban: Boolean = false,
                val lanes: List<Lane> = listOf(),
                val location: List<Double> = listOf(),
                val mapbox_streets_v8: MapboxStreetsV8 = MapboxStreetsV8(),
                val `out`: Int = 0,
                val toll_collection: TollCollection = TollCollection(),
                val turn_duration: Double = 0.0,
                val turn_weight: Double = 0.0,
                val weight: Double = 0.0
            ) {
                data class Lane(
                    val active: Boolean = false,
                    val indications: List<String> = listOf(),
                    val valid: Boolean = false,
                    val valid_indication: String = ""
                )

                data class MapboxStreetsV8(
                    val `class`: String = ""
                )

                data class TollCollection(
                    val type: String = ""
                )
            }

            data class Maneuver(
                val bearing_after: Int = 0,
                val bearing_before: Int = 0,
                val exit: Int = 0,
                val instruction: String = "",
                val location: List<Double> = listOf(),
                val modifier: String = "",
                val type: String = ""
            )

            data class VoiceInstruction(
                val announcement: String = "",
                val distanceAlongGeometry: Double = 0.0,
                val ssmlAnnouncement: String = ""
            )
        }
    }
}
