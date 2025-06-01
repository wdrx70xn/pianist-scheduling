package org.acme.kotlin.schooltimetabling.rest

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.kotlin.schooltimetabling.domain.Lesson
import org.acme.kotlin.schooltimetabling.domain.Teacher
import org.acme.kotlin.schooltimetabling.domain.Timeslot
import org.acme.kotlin.schooltimetabling.domain.Timetable
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import java.time.DayOfWeek.FRIDAY
import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.THURSDAY
import java.time.DayOfWeek.TUESDAY
import java.time.DayOfWeek.WEDNESDAY
import java.time.LocalTime

@Tag(name = "Demo data", description = "Timefold-provided demo school timetable data.")
@Path("demo-data")
class TimetableDemoResource {

    enum class DemoData {
        SMALL, LARGE
    }

    @APIResponses(
        value = [APIResponse(
            responseCode = "200", description = "List of demo data represented as IDs.", content = [Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = Schema(implementation = DemoData::class, type = SchemaType.ARRAY)
            )]
        )]
    )
    @Operation(summary = "List demo data.")
    @GET
    fun list(): Array<DemoData> {
        return DemoData.entries.toTypedArray()
    }

    @APIResponses(
        value = [APIResponse(
            responseCode = "200", description = "Unsolved demo timetable.", content = [Content(
                mediaType = MediaType.APPLICATION_JSON, schema = Schema(implementation = Timetable::class)
            )]
        )]
    )
    @Operation(summary = "Find an unsolved demo timetable by ID.")
    @GET
    @Path("/{demoDataId}")
    fun generate(
        @Parameter(
            description = "Unique identifier of the demo data.", required = true
        ) @PathParam("demoDataId") demoData: DemoData
    ): Response {
        val timeslots: MutableList<Timeslot> = ArrayList(10)
        var nextTimeslotId = 0L
        timeslots.add(Timeslot(nextTimeslotId++.toString(), MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)))
        timeslots.add(Timeslot(nextTimeslotId++.toString(), MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)))
        timeslots.add(Timeslot(nextTimeslotId++.toString(), MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)))
        timeslots.add(Timeslot(nextTimeslotId++.toString(), MONDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)))
        timeslots.add(Timeslot(nextTimeslotId++.toString(), MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)))
        timeslots.add(Timeslot(nextTimeslotId++.toString(), TUESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)))
        timeslots.add(Timeslot(nextTimeslotId++.toString(), TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)))
        timeslots.add(Timeslot(nextTimeslotId++.toString(), TUESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)))
        timeslots.add(Timeslot(nextTimeslotId++.toString(), TUESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)))
        timeslots.add(Timeslot(nextTimeslotId++.toString(), TUESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)))
        if (demoData == DemoData.LARGE) {
            timeslots.add(Timeslot(nextTimeslotId++.toString(), WEDNESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), WEDNESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), WEDNESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), WEDNESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), WEDNESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), THURSDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), THURSDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), THURSDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), THURSDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), THURSDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), FRIDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), FRIDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), FRIDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)))
            timeslots.add(Timeslot(nextTimeslotId++.toString(), FRIDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)))
            timeslots.add(Timeslot(nextTimeslotId.toString(), FRIDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)))
        }
        val teachers: MutableList<Teacher> = ArrayList<Teacher>(3)
        var nextRoomId = 0L
        teachers.add(Teacher(nextRoomId++.toString(), "Room A"))
        teachers.add(Teacher(nextRoomId++.toString(), "Room B"))
        teachers.add(Teacher(nextRoomId++.toString(), "Room C"))
        if (demoData == DemoData.LARGE) {
            teachers.add(Teacher(nextRoomId++.toString(), "Room D"))
            teachers.add(Teacher(nextRoomId++.toString(), "Room E"))
            teachers.add(Teacher(nextRoomId.toString(), "Room F"))
        }
        val lessons: MutableList<Lesson> = ArrayList<Lesson>()
        var nextLessonId = 0L
        lessons.add(Lesson(nextLessonId++.toString(), "Math", "9th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Math", "9th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Physics", "9th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Chemistry", "9th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Biology", "9th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "History", "9th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "English", "9th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "English", "9th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Spanish", "9th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Spanish", "9th grade"))
        if (demoData == DemoData.LARGE) {
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "ICT", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physics", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Geography", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Geology", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "History", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "English", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Drama", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Art", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Art", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "9th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "9th grade"))
        }
        lessons.add(Lesson(nextLessonId++.toString(), "Math", "10th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Math", "10th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Math", "10th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Physics", "10th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Chemistry", "10th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "French", "10th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Geography", "10th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "History", "10th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "English", "10th grade"))
        lessons.add(Lesson(nextLessonId++.toString(), "Spanish", "10th grade"))
        if (demoData == DemoData.LARGE) {
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "ICT", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physics", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Biology", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Geology", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "History", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "English", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "English", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Drama", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Art", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Art", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "10th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "ICT", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physics", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Chemistry", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "French", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physics", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Geography", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Biology", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Geology", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "History", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "History", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "English", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "English", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "English", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Spanish", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Drama", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Art", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Art", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "11th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Math", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "ICT", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physics", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Chemistry", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "French", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physics", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Geography", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Biology", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Geology", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "History", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "History", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "English", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "English", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "English", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Spanish", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Drama", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Art", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Art", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "12th grade"))
            lessons.add(Lesson(nextLessonId++.toString(), "Physical education", "12th grade"))
            lessons.add(Lesson(nextLessonId.toString(), "Physical education", "12th grade"))
        }
        return Response.ok(Timetable(demoData.name, timeslots, teachers, lessons))
            .build()
    }
}
