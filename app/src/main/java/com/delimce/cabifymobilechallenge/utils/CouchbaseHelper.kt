package com.delimce.cabifymobilechallenge.utils

import android.app.Application
import android.content.Context
import com.couchbase.lite.*
import com.couchbase.lite.internal.support.Log
import java.io.ByteArrayInputStream
import java.io.InputStream
import com.couchbase.lite.CouchbaseLiteException


@Suppress("IMPLICIT_CAST_TO_ANY")
open class CouchbaseHelper(context: Context) : Application() {

    private val databaseName = "cabify_db"
    var database: Database

    init {
        val config = DatabaseConfiguration(context)
        database = Database(databaseName, config)
        println("database connected")

    }

    /**
     * save document to database
     */
    fun saveDoc(doc: MutableDocument) {
        try {
            database.save(doc)
        } catch (e: CouchbaseLiteException) {
            Log.e(LogDomain.ALL, e.toString())
        }
    }


    fun getDoc(docId: String, edit: Boolean = false): Document? {
        val doc = database.getDocument(docId)
        return if(doc!=null && edit){
             doc.toMutable()
        }else{
             doc
        }
    }

    fun createDoc(id: String = ""): MutableDocument {
        return if (id.isEmpty()) MutableDocument() else MutableDocument(id)
    }

    fun getDocumentOrCreateIt(docId:String): MutableDocument {
        return (getDoc(docId,true) ?: createDoc(docId)) as MutableDocument
    }


    /**
     * delete document from database
     */
    fun deleteDoc(docId: String) {
        try {
            val doc = database.getDocument(docId)
            if (doc != null) database.delete(doc)
        } catch (e: CouchbaseLiteException) {
            Log.e(LogDomain.ALL, "Cannot delete document", e)
        }

    }

    /**
     * close database
     */
    fun close() {
        try {
            database.close()
            println("database closed")
        } catch (e: CouchbaseLiteException) {
            Log.e(LogDomain.ALL, "Cannot close database", e)
        }

    }


    /**
     * get all fields by type
     */
    fun getQueryAllRecordsByType(type: String): Query {
        val query = QueryBuilder
                .select(SelectResult.all())
                .from(DataSource.database(database))
                .where(Expression.property("type").equalTo(Expression.string(type)))
        return query
    }

    /**
     * create attachment
     */
    fun createBlobFromString(attachment: String): Blob {
        val attach = ByteArrayInputStream(attachment.toByteArray())
        return Blob("image/svg+xml", attach)
    }


    /**
     * get attachment
     */
    fun getAttachmentByDocID(docId: String): InputStream {
        val doc = database.getDocument(docId) as Document
        val taskBlob = doc.getBlob("attach")
        return taskBlob.contentStream
    }


}

