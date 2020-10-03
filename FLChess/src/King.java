/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jacob Freeland
 */
public class King extends Piece
{
    public King()
    {
        this.white = true;
        this.charRep = 'K';
    }
    
    public King(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'K' : 'k');
    }
}