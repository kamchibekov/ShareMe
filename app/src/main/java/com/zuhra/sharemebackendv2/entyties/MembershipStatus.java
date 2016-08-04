package com.zuhra.sharemebackendv2.entyties;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class MembershipStatus
{
  private String ownerId;
  private String objectId;
  private String name;
  private java.util.Date updated;
  private java.util.Date created;
  public String getOwnerId()
  {
    return ownerId;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

                                                    
  public MembershipStatus save()
  {
    return Backendless.Data.of( MembershipStatus.class ).save( this );
  }

  public Future<MembershipStatus> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MembershipStatus> future = new Future<MembershipStatus>();
      Backendless.Data.of( MembershipStatus.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<MembershipStatus> callback )
  {
    Backendless.Data.of( MembershipStatus.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( MembershipStatus.class ).remove( this );
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
      Backendless.Data.of( MembershipStatus.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( MembershipStatus.class ).remove( this, callback );
  }

  public static MembershipStatus findById( String id )
  {
    return Backendless.Data.of( MembershipStatus.class ).findById( id );
  }

  public static Future<MembershipStatus> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MembershipStatus> future = new Future<MembershipStatus>();
      Backendless.Data.of( MembershipStatus.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<MembershipStatus> callback )
  {
    Backendless.Data.of( MembershipStatus.class ).findById( id, callback );
  }

  public static MembershipStatus findFirst()
  {
    return Backendless.Data.of( MembershipStatus.class ).findFirst();
  }

  public static Future<MembershipStatus> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MembershipStatus> future = new Future<MembershipStatus>();
      Backendless.Data.of( MembershipStatus.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<MembershipStatus> callback )
  {
    Backendless.Data.of( MembershipStatus.class ).findFirst( callback );
  }

  public static MembershipStatus findLast()
  {
    return Backendless.Data.of( MembershipStatus.class ).findLast();
  }

  public static Future<MembershipStatus> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MembershipStatus> future = new Future<MembershipStatus>();
      Backendless.Data.of( MembershipStatus.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<MembershipStatus> callback )
  {
    Backendless.Data.of( MembershipStatus.class ).findLast( callback );
  }

  public static BackendlessCollection<MembershipStatus> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( MembershipStatus.class ).find( query );
  }

  public static Future<BackendlessCollection<MembershipStatus>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<MembershipStatus>> future = new Future<BackendlessCollection<MembershipStatus>>();
      Backendless.Data.of( MembershipStatus.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<MembershipStatus>> callback )
  {
    Backendless.Data.of( MembershipStatus.class ).find( query, callback );
  }
}