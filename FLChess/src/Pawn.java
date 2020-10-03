/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jacob Freeland
 */
public class Pawn extends Piece
{
    boolean hasMoved = false;
    
    public Pawn()
    {
        this.white = true;
        this.charRep = 'P';
    }
    
    public Pawn(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'P' : 'p');
    }
}