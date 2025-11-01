package com.example.voicerecorder.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        RecordingSession::class,
        AudioChunk::class,
        TranscriptSegment::class,
        SummaryEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun chunkDao(): ChunkDao
    abstract fun transcriptDao(): TranscriptDao
    abstract fun summaryDao(): SummaryDao
}
