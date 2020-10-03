/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jacob Freeland
 */
public class Rook extends Piece
{
    public Rook()
    {
        this.white = true;
        this.charRep = 'R';
    }
    
    public Rook(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'R' : 'r');
    }
}
