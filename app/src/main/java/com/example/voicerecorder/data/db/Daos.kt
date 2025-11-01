package com.example.voicerecorder.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(s: RecordingSession)

    @Query("SELECT * FROM RecordingSession ORDER BY startedAt DESC")
    fun observeAll(): Flow<List<RecordingSession>>

    @Query("SELECT * FROM RecordingSession WHERE sessionId = :id")
    fun observe(id: String): Flow<RecordingSession?>
}

@Dao
interface ChunkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(c: AudioChunk)

    @Query("SELECT * FROM AudioChunk WHERE sessionId = :sid ORDER BY sequence")
    fun observeBySession(sid: String): Flow<List<AudioChunk>>

    @Query("SELECT * FROM AudioChunk WHERE (uploaded = 0 OR transcribed = 0)")
    suspend fun pending(): List<AudioChunk>
}

@Dao
interface TranscriptDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(list: List<TranscriptSegment>)

    @Query("SELECT * FROM TranscriptSegment WHERE sessionId = :sid ORDER BY sequence")
    fun observe(sid: String): Flow<List<TranscriptSegment>>
}

@Dao
interface SummaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(s: SummaryEntity)

    @Query("SELECT * FROM SummaryEntity WHERE sessionId = :sid")
    fun observe(sid: String): Flow<SummaryEntity?>
}
