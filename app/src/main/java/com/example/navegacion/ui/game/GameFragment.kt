package com.example.navegacion.ui.game

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.navegacion.R
import com.example.navegacion.databinding.FragmentGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameViewModel =
            ViewModelProvider(this).get(GameViewModel::class.java)

        binding = FragmentGameBinding.inflate(inflater, container, false)
        gameViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnDiceRoll.setOnClickListener {
            val images = listOf(
                R.drawable.dice_1,
                R.drawable.dice_2,
                R.drawable.dice_3,
                R.drawable.dice_4,
                R.drawable.dice_5,
                R.drawable.dice_6
            )
            binding.imgSpaceForDice.setBackgroundResource(R.drawable.animation_dice)
            val backgroundAnimation = binding.imgSpaceForDice.background as AnimationDrawable
            backgroundAnimation.start()

            lifecycleScope.launch(context = Dispatchers.Main) {
                delay(1000)
                val number = (0..5).shuffled().first()
                binding.imgSpaceForDice.setImageResource(images[number])
            }
        }
    }
}