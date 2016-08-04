package com.zuhra.sharemebackendv2.entyties;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Codes
{
  private java.util.Date updated;
  private java.util.Date created;
  private Integer num;
  private String ownerId;
  private String objectId;
  public java.util.Date getUpdated()
  {
    return updated;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public Integer getNum()
  {
    return num;
  }

  public void setNum( Integer num )
  {
    this.num = num;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getObjectId()
  {
    return objectId;
  }

                                                    
  public Codes save()
  {
    return Backendless.Data.of( Codes.class ).save( this );
  }

  public Future<Codes> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Codes> future = new Future<Codes>();
      Backendless.Data.of( Codes.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Codes> callback )
  {
    Backendless.Data.of( Codes.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Codes.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( Codes.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Codes.class ).remove( this, callback );
  }

  public static Codes findById( String id )
  {
    return Backendless.Data.of( Codes.class ).findById( id );
  }

  public static Future<Codes> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Codes> future = new Future<Codes>();
      Backendless.Data.of( Codes.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Codes> callback )
  {
    Backendless.Data.of( Codes.class ).findById( id, callback );
  }

  public static Codes findFirst()
  {
    return Backendless.Data.of( Codes.class ).findFirst();
  }

  public static Future<Codes> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Codes> future = new Future<Codes>();
      Backendless.Data.of( Codes.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Codes> callback )
  {
    Backendless.Data.of( Codes.class ).findFirst( callback );
  }

  public static Codes findLast()
  {
    return Backendless.Data.of( Codes.class ).findLast();
  }

  public static Future<Codes> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Codes> future = new Future<Codes>();
      Backendless.Data.of( Codes.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Codes> callback )
  {
    Backendless.Data.of( Codes.class ).findLast( callback );
  }

  public static BackendlessCollection<Codes> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Codes.class ).find( query );
  }

  public static Future<BackendlessCollection<Codes>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Codes>> future = new Future<BackendlessCollection<Codes>>();
      Backendless.Data.of( Codes.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Codes>> callback )
  {
    Backendless.Data.of( Codes.class ).find( query, callback );
  }
}