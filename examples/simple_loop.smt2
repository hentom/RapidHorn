( set-logic HORN )
( declare-rel loc0 ( Int ) )
( declare-rel error ( ) )
( declare-rel loc1 ( Int ) )
( declare-rel loc2 ( Int Int ) )
( declare-rel loc3 ( Int Int Int ) )
( declare-rel loc4 ( Int Int Int ) )
( declare-rel loc5 ( Int Int Int ) )
( declare-rel loc6 ( Int Int Int ) )
( declare-rel loc7 ( Int Int Int ) )
( declare-rel loc8 ( Int Int Int ) )
( declare-var k Int )
( declare-var r Int )
( declare-var i Int )
( rule ( loc0 k ) )
( rule ( => ( and ( loc0 k ) ( >= k 0 ) ) ( loc1 k ) ) )
( rule ( => ( loc1 k ) ( loc2 k 0 ) ) )
( rule ( => ( loc2 k r ) ( loc3 k r 0 ) ) )
( rule ( => ( and ( loc3 k r i ) ( < i k ) ) ( loc4 k r i ) ) )
( rule ( => ( loc4 k r i ) ( loc5 k ( + r 2 ) i ) ) )
( rule ( => ( loc5 k r i ) ( loc6 k r ( + i 1 ) ) ) )
( rule ( => ( loc6 k r i ) ( loc3 k r i ) ) )
( rule ( => ( and ( loc3 k r i ) ( >= i k ) ) ( loc7 k r i ) ) )
( rule ( => ( and ( loc7 k r i ) ( = r ( * 2 k ) ) ) ( loc8 k r i ) ) )
( rule ( => ( and ( loc7 k r i ) (not ( = r ( * 2 k ) ) ) ) error ) )
( query error )
