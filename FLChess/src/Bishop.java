/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jacob Freeland
 */
public class Bishop extends Piece
{
    public Bishop()
    {
        this.white = true;
        this.charRep = 'B';
    }
    
    public Bishop(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'B' : 'b');
    }
}