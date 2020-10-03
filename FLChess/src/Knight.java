/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jacob Freeland
 */
public class Knight extends Piece
{
    public Knight()
    {
        this.white = true;
        this.charRep = 'N';
    }
    
    public Knight(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'N' : 'n');
    }
}