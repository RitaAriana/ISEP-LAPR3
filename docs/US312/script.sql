
CREATE OR REPLACE FUNCTION get_container_position (v_numberId int, v_clientNumber int) return varchar

IS

    v_idCargoManifest int;
    v_CargoManifestUnloadId int;
    v_mmsi varchar(255);
    v_locationName varchar(255);
    v_locationNameAux varchar(255);
    v_arrivalDate timestamp;
    v_arrivalDate_aux timestamp;
    v_phases int;
    v_OwnerNumber int;
    idContainer int;

    Cursor cargos IS
        Select CARGOMANIFESTLOADID
        from CargoManifestContainer
        where CONTAINERNUMBERID=v_numberId;
        
begin

   open cargos;
   LOOP 
   fetch cargos into v_idCargoManifest;
   exit when cargos%notfound;
   
   select clientOwner
   into v_OwnerNumber
   from CargoManifestContainer
   where CARGOMANIFESTLOADID=v_idCargoManifest; 
   
   if v_OwnerNumber = v_clientNumber then
   
    select CargoManifestUnloadId
    into v_CargoManifestUnloadId
    from CargoManifestContainer
    where PhasesCargoManifestLoadId=v_idCargoManifest; 
   
        if v_CargoManifestUnloadId IS NULL then
    
            Select SHIPMMSICODE into v_mmsi
            from CargoManifestLoad
            where ID = v_idCargoManifest;
    
            Select SHIPNAME into v_locationName
            from Ship
            where mmsiCode = v_mmsi;
    
            return ('SHIP, '|| (v_locationName)); 
            end if;
    end if;
    
   END LOOP;
   close cargos;
   
   open cargos;
   LOOP 
   fetch cargos into v_idCargoManifest;
   exit when cargos%notfound;
   
   select clientOwner
   into v_OwnerNumber
   from CargoManifestContainer
   where CARGOMANIFESTLOADID=v_idCargoManifest; 
   
   if v_OwnerNumber = v_clientNumber then
   
    Select phasesId into v_phases
    from CargoManifestUnload
    where v_idCargoManifest=phasescargomanifestloadid; 
   
    Select realArrivalDate into v_arrivalDate_aux
    from phases
    where v_idCargoManifest=CargoManifestLoadId and id=v_phases; 
   
    Select destination into v_locationNameAux
    from phases
    where v_idCargoManifest=CargoManifestLoadId and id=v_phases; 
   
    if v_arrivalDate IS NULL then
    v_arrivalDate:=v_arrivalDate_aux;
    v_locationName:=v_locationNameAux;
    else if v_arrivalDate_aux>v_arrivalDate then
    v_arrivalDate:=v_arrivalDate_aux;
    v_locationName:=v_locationNameAux;
    end if;
    end if;
   end if;
   
   END LOOP;
   
   if v_locationName IS NULL then
    RETURN ('Error 11: Container with id = ' || (idContainer) || ' is not leased by the client.');
   else 
    RETURN ('PORT, '|| (v_locationName)); 
   end if;
   close cargos;
    
    exception
        when no_data_found then
            return('Error 10: The id = ' ||(v_numberId)||  ' of the container is not valid.');
    end;