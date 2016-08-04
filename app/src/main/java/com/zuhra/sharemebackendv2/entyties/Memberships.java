package com.zuhra.sharemebackendv2.entyties;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Memberships
{
  private java.util.Date end_date;
  private java.util.Date start_date;
  private String objectId;
  private java.util.Date created;
  private Integer is_admin;
  private java.util.Date updated;
  private String ownerId;
  private Integer is_free;
  private java.util.List<BackendlessUser> user_id;
  private java.util.List<com.zuhra.sharemebackendv2.entyties.MembershipStatus> status_id;
  private java.util.List<com.zuhra.sharemebackendv2.entyties.Orgs> org_id;
  public java.util.Date getEnd_date()
  {
    return end_date;
  }

  public void setEnd_date( java.util.Date end_date )
  {
    this.end_date = end_date;
  }

  public java.util.Date getStart_date()
  {
    return start_date;
  }

  public void setStart_date( java.util.Date start_date )
  {
    this.start_date = start_date;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public Integer getIs_admin()
  {
    return is_admin;
  }

  public void setIs_admin( Integer is_admin )
  {
    this.is_admin = is_admin;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public Integer getIs_free()
  {
    return is_free;
  }

  public void setIs_free( Integer is_free )
  {
    this.is_free = is_free;
  }

  public java.util.List<BackendlessUser> getUser_id()
  {
    return user_id;
  }

  public void setUser_id( java.util.List<BackendlessUser> user_id )
  {
    this.user_id = user_id;
  }

  public java.util.List<com.zuhra.sharemebackendv2.entyties.MembershipStatus> getStatus_id()
  {
    return status_id;
  }

  public void setStatus_id( java.util.List<com.zuhra.sharemebackendv2.entyties.MembershipStatus> status_id )
  {
    this.status_id = status_id;
  }

  public java.util.List<com.zuhra.sharemebackendv2.entyties.Orgs> getOrg_id()
  {
    return org_id;
  }

  public void setOrg_id( java.util.List<com.zuhra.sharemebackendv2.entyties.Orgs> org_id )
  {
    this.org_id = org_id;
  }

                                                    
  public Memberships save()
  {
    return Backendless.Data.of( Memberships.class ).save( this );
  }

  public Future<Memberships> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Memberships> future = new Future<Memberships>();
      Backendless.Data.of( Memberships.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Memberships> callback )
  {
    Backendless.Data.of( Memberships.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Memberships.class ).remove( this );
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
      Backendless.Data.of( Memberships.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Memberships.class ).remove( this, callback );
  }

  public static Memberships findById( String id )
  {
    return Backendless.Data.of( Memberships.class ).findById( id );
  }

  public static Future<Memberships> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Memberships> future = new Future<Memberships>();
      Backendless.Data.of( Memberships.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Memberships> callback )
  {
    Backendless.Data.of( Memberships.class ).findById( id, callback );
  }

  public static Memberships findFirst()
  {
    return Backendless.Data.of( Memberships.class ).findFirst();
  }

  public static Future<Memberships> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Memberships> future = new Future<Memberships>();
      Backendless.Data.of( Memberships.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Memberships> callback )
  {
    Backendless.Data.of( Memberships.class ).findFirst( callback );
  }

  public static Memberships findLast()
  {
    return Backendless.Data.of( Memberships.class ).findLast();
  }

  public static Future<Memberships> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Memberships> future = new Future<Memberships>();
      Backendless.Data.of( Memberships.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Memberships> callback )
  {
    Backendless.Data.of( Memberships.class ).findLast( callback );
  }

  public static BackendlessCollection<Memberships> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Memberships.class ).find( query );
  }

  public static Future<BackendlessCollection<Memberships>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Memberships>> future = new Future<BackendlessCollection<Memberships>>();
      Backendless.Data.of( Memberships.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Memberships>> callback )
  {
    Backendless.Data.of( Memberships.class ).find( query, callback );
  }
}