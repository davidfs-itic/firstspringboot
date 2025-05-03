package com.dfdocencia.backend.firstspringboot.repositori

import com.dfdocencia.backend.firstspringboot.model.Torneig
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.Instant

interface TorneigRepositori : MongoRepository<Torneig, ObjectId>{
/*       fun lloc(lloc: String): List<Torneig>*/
}

