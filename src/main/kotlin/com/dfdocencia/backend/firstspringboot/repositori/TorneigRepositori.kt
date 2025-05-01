package com.dfdocencia.backend.firstspringboot.repositori

import com.dfdocencia.backend.firstspringboot.model.Torneig
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.Instant

interface TorneigRepositori : MongoRepository<Torneig, ObjectId>{
    fun findbyId(id: ObjectId): Torneig?
    fun findByName(nom: String): Torneig?
    fun findbydata(instant: Instant): Torneig?
    fun findBetween(Start:Instant, End:Instant): List<Torneig>
    fun findbylloc(lloc: String): Torneig?
}

