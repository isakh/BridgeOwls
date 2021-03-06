package ws.isak.bridgeowls.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import ws.isak.bridgeowls.common.Shared;
import ws.isak.bridgeowls.common.MatchCardData;

/*
 *
 * @author isak
 */

public class MatchCardDataORM {

    private static final String TAG="MatchCardDataORM";

    private static final String TABLE_NAME = "match_card_data";

    private static final String COMMA_SEP = ", ";


    private static final String COLUMN_CARD_ID_TYPE = "INTEGER PRIMARY KEY"; //TODO does this make sense?
    private static final String COLUMN_CARD_ID = "cardID";

    private static final String COLUMN_SPECIES_NAME_TYPE = "STRING";
    private static final String COLUMN_SPECIES_NAME = "speciesName";

    private static final String COLUMN_UTTERANCE_TYPE_TYPE = "STRING";
    private static final String COLUMN_UTTERANCE_TYPE = "utteranceType";

    private static final String COLUMN_INDIVIDUAL_TYPE_TYPE = "STRING";
    private static final String COLUMN_INDIVIDUAL_TYPE = "individualType";

    //sqlite cannot deal with booleans, pairedImagesDiffer and firstImageUsed become INTEGERS
    //private static final String COLUMN_PAIRED_IMAGES_DIFFER_TYPE = "INTEGER";
    //private static final String COLUMN_PAIRED_IMAGES_DIFFER = "pairedImagesDiffer";

    //private static final String COLUMN_FIRST_IMAGE_USED_TYPE = "INTEGER";
    //private static final String COLUMN_FIRST_IMAGE_USED = "firstImageUsed";

    private static final String COLUMN_IMAGE_URI0_TYPE = "STRING";
    private static final String COLUMN_IMAGE_URI0 = "imageURI0";

    private static final String COLUMN_IMAGE_URI1_TYPE = "STRING";
    private static final String COLUMN_IMAGE_URI1 = "imageURI1";

    private static final String COLUMN_IMAGE_URI2_TYPE = "STRING";
    private static final String COLUMN_IMAGE_URI2 = "imageURI2";

    private static final String COLUMN_IMAGE_URI3_TYPE = "STRING";
    private static final String COLUMN_IMAGE_URI3 = "imageURI3";

    private static final String COLUMN_AUDIO_URI_TYPE = "STRING";
    private static final String COLUMN_AUDIO_URI = "audioURI";

    private static final String COLUMN_SAMPLE_DURATION_TYPE = "INTEGER";        //SQLite Integer can handle 8 byte long
    private static final String COLUMN_SAMPLE_DURATION = "sampleDuration";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME +
            " (" +
            COLUMN_CARD_ID + " " + COLUMN_CARD_ID_TYPE + COMMA_SEP +
            COLUMN_SPECIES_NAME + " " + COLUMN_SPECIES_NAME_TYPE + COMMA_SEP +
            COLUMN_INDIVIDUAL_TYPE + " " + COLUMN_INDIVIDUAL_TYPE_TYPE + COMMA_SEP +
            COLUMN_UTTERANCE_TYPE + " " + COLUMN_UTTERANCE_TYPE_TYPE + COMMA_SEP +
            //COLUMN_PAIRED_IMAGES_DIFFER + " " + COLUMN_PAIRED_IMAGES_DIFFER_TYPE + COMMA_SEP +
            //COLUMN_FIRST_IMAGE_USED + " " + COLUMN_FIRST_IMAGE_USED_TYPE + COMMA_SEP +
            COLUMN_IMAGE_URI0 + " " + COLUMN_IMAGE_URI0_TYPE + COMMA_SEP +
            COLUMN_IMAGE_URI1 + " " + COLUMN_IMAGE_URI1_TYPE + COMMA_SEP +
            COLUMN_IMAGE_URI2 + " " + COLUMN_IMAGE_URI2_TYPE + COMMA_SEP +
            COLUMN_IMAGE_URI3 + " " + COLUMN_IMAGE_URI3_TYPE + COMMA_SEP +
            COLUMN_AUDIO_URI + " " + COLUMN_AUDIO_URI_TYPE + COMMA_SEP +
            COLUMN_SAMPLE_DURATION + " " + COLUMN_SAMPLE_DURATION_TYPE +
            ")";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    // ===========================================================================================

    public static boolean matchCardDataRecordsInDatabase(Context context) {
        //Log.d (TAG, "method matchCardDataRecordsInDatabase");

        DatabaseWrapper databaseWrapper = Shared.databaseWrapper;
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        boolean recordsExist = false;

        if (database != null) {
            Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            Log.d(TAG, "method matchCardDataRecordsInDatabase: Checked " + cursor.getCount() + " MatchCardData records...");

            if (cursor.getCount() > 0) {
                recordsExist = true;
            }
            cursor.close();
        }
        database.close();
        return recordsExist;
    }

    //FIXME these methods can be made generic if the TypeOfDataORM.TABLE_NAME is passed in
    public static int numMatchCardDataRecordsInDatabase(Context context) {
        //Log.d (TAG, "method numMatchCardDataRecordsInDatabase");

        DatabaseWrapper databaseWrapper = Shared.databaseWrapper;
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        int numRecords = 0;

        if (database != null) {
            Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            Log.d(TAG, "method numMatchCardDataRecordsInDatabase: There are: " + cursor.getCount() + " MatchCardData records...");

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    numRecords++;
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        return numRecords;
    }

    //method isMatchCardDataInDB takes a MatchCardData object and checks whether it has been used as a
    //primary key yet in the database - used to check existence and uniqueness when loading cards.
    public static boolean isMatchCardDataInDB(MatchCardData cardToCheck) {
        Log.d (TAG, "method isMatchCardDataInDB: check cardToCheck: " + cardToCheck);
        DatabaseWrapper databaseWrapper = Shared.databaseWrapper;
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        boolean cardExists = false;     //false unless found in database
        int cardID = cardToCheck.getCardID();
        Log.d (TAG, " ... checking cardID: " + cardID);
        if (database != null) {
            Log.d (TAG, "method isMatchCardDataInDB: searching...");
            Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CARD_ID + " ='" + cardID + "'", null);
            //FIXME - this prevent's SQL injection: Cursor cursor = database.rawQuery("SELECT * FROM " + UserDataORM.TABLE_NAME + " WHERE " + UserDataORM.COLUMN_USER_NAME_ID + " =?", userName);
            if (cursor.getCount() > 0) {
                cardExists = true;
            }
            else {
                //Toast.makeText(Shared.context, "new match game card added to database" , Toast.LENGTH_SHORT).show();
            }
            cursor.close();
            database.close();
        }
        Log.d (TAG, " ..... searching ..... cardExists: " + cardExists);
        return  cardExists;
    }


    public static MatchCardData getMatchCardData(int cardID) {
        Log.d (TAG, "method getMatchGameData returns a list of matchCardData objects with cardID " + cardID);

        DatabaseWrapper databaseWrapper =  Shared.databaseWrapper;
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        MatchCardData matchCardDataToReturn = null;

        if (database !=  null) {
            Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CARD_ID + " ='" + cardID + "'", null);
            //FIXME - this prevent's SQL injection: Cursor cursor = database.rawQuery("SELECT * FROM " + UserDataORM.TABLE_NAME + " WHERE " + UserDataORM.COLUMN_USER_NAME_ID + " =?", userName);
            Log.d (TAG, "method getMatchCardData: Loaded " + cursor.getCount() + " MatchCardData records... this should always only be 1");
            if (matchCardDataRecordsInDatabase(Shared.context)) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    MatchCardData matchCardDataAtCursor = cursorToMatchCardData(cursor);
                    //Check the state of all MatchCardData fields here
                    Log.d (TAG, "... PARSE: MatchCardData object to return:" +
                            " | cardID: " + matchCardDataAtCursor.getCardID() +
                            " | speciesName: " + matchCardDataAtCursor.getSpeciesName() +
                            " | individualType: " + matchCardDataAtCursor.getIndividualType() +
                            " | utteranceType: " + matchCardDataAtCursor.getUtteranceType() +
                            " | pairedImagesDiffer: " + matchCardDataAtCursor.getPairedImageDiffer() +
                            " | firstImageUsed: " + matchCardDataAtCursor.getFirstImageUsed() +
                            " | imageURI0: " + matchCardDataAtCursor.getImageURI0() +
                            " | imageURI1: " + matchCardDataAtCursor.getImageURI1() +
                            " | imageURI2: " + matchCardDataAtCursor.getImageURI2() +
                            " | imageURI3: " + matchCardDataAtCursor.getImageURI3() +
                            " | audioURI: " + matchCardDataAtCursor.getAudioURI() +
                            " | sampleDuration: "+ matchCardDataAtCursor.getSampleDuration());
                    matchCardDataToReturn = matchCardDataAtCursor;
                    cursor.moveToNext();
                }
                cursor.close();
                database.close();
            }
        }
        return matchCardDataToReturn;
    }


    public static boolean insertMatchCardData(MatchCardData matchCardData){
        Log.d (TAG, "method insertMatchCardData tries to insert a new MatchCardData object into the database");

        boolean success = false;

        Log.d(TAG, "method insertMatchCardData: creating ContentValues");
        ContentValues values = matchCardDataToContentValues(matchCardData);

        DatabaseWrapper databaseWrapper = Shared.databaseWrapper;
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        try {
            if (database != null) {
                long rowID = database.insert(TABLE_NAME, "null", values);
                Log.d(TAG, "method insertMatchCardData: Inserted new MatchCardData into rowID: " + rowID);
                success = true;
            }
        } catch (SQLiteException sqlex) {
            Log.e(TAG, "method insertMatchCardData: Failed to insert MatchCardData[" + matchCardData.getCardID() + "] due to: " + sqlex);
            sqlex.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return success;
    }


    //method matchCardDataToContentValues packs a matchCardData object into a ContentValues map for
    //use with SQL inserts
    private static ContentValues matchCardDataToContentValues(MatchCardData matchCardData) {
        Log.d (TAG, "private method matchCardDataToContentValues");
        ContentValues values = new ContentValues();

        values.put (COLUMN_CARD_ID, matchCardData.getCardID());
        values.put (COLUMN_SPECIES_NAME, matchCardData.getSpeciesName());
        values.put (COLUMN_INDIVIDUAL_TYPE, matchCardData.getIndividualType());
        values.put (COLUMN_UTTERANCE_TYPE, matchCardData.getUtteranceType());

        //if (!matchCardData.getPairedImageDiffer()) { values.put (COLUMN_PAIRED_IMAGES_DIFFER, 0);}       //TODO Check logic
        //else{ values.put (COLUMN_PAIRED_IMAGES_DIFFER, 1); }
        //if (!matchCardData.getFirstImageUsed()) { values.put (COLUMN_FIRST_IMAGE_USED, 0); }             //TODO Check logic
        //else { values.put (COLUMN_FIRST_IMAGE_USED, 1); }

        values.put (COLUMN_IMAGE_URI0, matchCardData.getImageURI0());
        values.put (COLUMN_IMAGE_URI1, matchCardData.getImageURI1());
        values.put (COLUMN_IMAGE_URI2, matchCardData.getImageURI2());
        values.put (COLUMN_IMAGE_URI3, matchCardData.getImageURI3());
        values.put (COLUMN_AUDIO_URI, matchCardData.getAudioURI());
        values.put (COLUMN_SAMPLE_DURATION, matchCardData.getSampleDuration());
        return values;
    }


    //method cursorToMatchCardData populates a MatchCardData object with data from the cursor
    private static MatchCardData cursorToMatchCardData(Cursor cursor) {
        Log.d (TAG, "method cursorToMatchCardData");
        MatchCardData cursorAtMatchCardData = new MatchCardData();

        cursorAtMatchCardData.setCardID (cursor.getInt(cursor.getColumnIndex(COLUMN_CARD_ID)));
        cursorAtMatchCardData.setSpeciesName(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIES_NAME)));      //FIXME solve string approach to species loading - currently overloading constructor
        cursorAtMatchCardData.setIndividualType(cursor.getString(cursor.getColumnIndex(COLUMN_INDIVIDUAL_TYPE)));
        cursorAtMatchCardData.setUtteranceType(cursor.getString(cursor.getColumnIndex(COLUMN_UTTERANCE_TYPE)));

        /* REMOVE PAIRED IMAGES DIFFER AND COLUMN FIRST IMAGE USED
        if (cursor.getInt(cursor.getColumnIndex(COLUMN_PAIRED_IMAGES_DIFFER)) == 1) { cursorAtMatchCardData.setPairedImageDiffer(true); }
        else if (cursor.getInt(cursor.getColumnIndex(COLUMN_PAIRED_IMAGES_DIFFER)) == 0) { cursorAtMatchCardData.setPairedImageDiffer(false); }
        else {
            Log.d (TAG, "ERROR: method cursorAtMatchCardData: mixerState: " + cursor.getInt(cursor.getColumnIndex(COLUMN_PAIRED_IMAGES_DIFFER)));
        }
        if (cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_IMAGE_USED)) == 1) { cursorAtMatchCardData.setFirstImageUsed(true); }
        else if (cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_IMAGE_USED)) == 0) { cursorAtMatchCardData.setFirstImageUsed(false); }
        else {
            Log.d (TAG, "ERROR: method cursorAtMatchCardData: isFirstImageUsed: " + cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_IMAGE_USED)));
        }
        */
        cursorAtMatchCardData.setImageURI0(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URI0)));
        cursorAtMatchCardData.setImageURI1(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URI1)));
        cursorAtMatchCardData.setImageURI2(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URI2)));
        cursorAtMatchCardData.setImageURI3(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URI3)));
        cursorAtMatchCardData.setAudioURI(cursor.getString(cursor.getColumnIndex(COLUMN_AUDIO_URI)));
        cursorAtMatchCardData.setSampleDuration((long) cursor.getInt(cursor.getColumnIndex(COLUMN_SAMPLE_DURATION)));

        return cursorAtMatchCardData;
    }
}
