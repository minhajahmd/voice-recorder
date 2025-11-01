package com.example.voicerecorder.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity
data class RecordingSession(
    @PrimaryKey val sessionId: String,
    val startedAt: Long,
    val endedAt: Long? = null,
    val status: String // ACTIVE, PAUSED, STOPPED, ERROR
)

@Entity(indices = [Index("sessionId"), Index("sequence")])
data class AudioChunk(
    @PrimaryKey val chunkId: String,
    val sessionId: String,
    val sequence: Long,
    val path: String,
    val durationMs: Long,
    val overlapMs: Long = 2000,
    val uploaded: Boolean = false,
    val transcribed: Boolean = false,
    val createdAt: Long
)

@Entity(indices = [Index("sessionId"), Index("sequence")])
data class TranscriptSegment(
    @PrimaryKey val segmentId: String,
    val sessionId: String,
    val sequence: Long,
    val text: String,
    val createdAt: Long
)

@Entity
data class SummaryEntity(
    @PrimaryKey val sessionId: String,
    val title: String? = null,
    val summary: String? = null,
    val actionItems: String? = null,
    val keyPoints: String? = null,
    val status: String, // IDLE, GENERATING, DONE, ERROR
    val error: String? = null,
    val updatedAt: Long
)
